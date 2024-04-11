import {
  getLoginUserUsingGet,
  updateUserPasswordUsingPost,
} from '@/services/backend/userController';
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';
import { Button, message, Space } from 'antd';
import Input from 'antd/lib/input';
import React, { useEffect, useState } from 'react';

// 假设有一个简单的密码强度评估函数，可以根据实际情况替换为更复杂的算法
const evaluatePasswordStrength = (password: string): keyof typeof passwordStrength => {
  // 这里只是一个简化的示例，实际应用可能需要更复杂的密码强度评估规则
  const maxLength = 16;
  const lengthCheck = password.length >= 8 && password.length <= maxLength;

  if (!lengthCheck) {
    return 'unlawful';
  }

  const hasUpper = password.match(/[A-Z]/g);
  const hasLower = password.match(/[a-z]/g);
  const hasDigit = password.match(/\d/g);
  const hasSpecial = password.match(/[!@#$%^&*(),.?":{}|<>]/g);
  // 纯数字密码为弱
  if (password.match(/^\d+$/)) {
    return 'weak';
  }
  // 默认为中等强度
  let strength = 'medium';
  // 符合特定组合或长度大于等于12的密码为强
  if (hasUpper && hasLower && hasDigit && (hasSpecial || password.length >= 12)) {
    strength = 'strong';
  }

  return strength;
};

const passwordStrength = {
  strong: <span className="strong">强</span>,
  medium: <span className="medium">中</span>,
  weak: <span className="weak">弱</span>,
  unlawful: <span className="unlawful">不合法</span>,
  default: <span className="default"></span>,
};

const SecurityView: React.FC = () => {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [passwordStrengthLevel, setPasswordStrengthLevel] =
    useState<keyof typeof passwordStrength>('weak');
  const [currentUser, setCurrentUser] = useState<API.LoginUserVO>();
  const getLoginUserInfo = async () => {
    const res = await getLoginUserUsingGet();
    setCurrentUser(res.data);
  };

  useEffect(() => {
    getLoginUserInfo();
    const currentStrength = evaluatePasswordStrength(password);
    setPasswordStrengthLevel(currentStrength);
  }, [password]);

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleConfirmPasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setConfirmPassword(event.target.value);
  };

  const ChangePassword = async () => {
    if (password !== confirmPassword) {
      message.error('两次输入的新密码不一致');
      return;
    }

    const passwordResult = evaluatePasswordStrength(confirmPassword);
    if (passwordResult === 'unlawful') {
      message.error('密码不合法');
      return;
    }

    const updatePassword: API.UserUpdatePasswordRequest = {
      id: currentUser?.id,
      userPassword: confirmPassword,
    };

    const res = await updateUserPasswordUsingPost(updatePassword);
    if (res.code === 0) {
      message.success('修改成功, 请重新登录');
      window.location.href = '/user/login';
      // 可以在此处清空密码输入框
      setPassword('');
      setConfirmPassword('');
    } else {
      message.error('修改失败');
    }
  };

  return (
    <>
      <Space direction="vertical">
        输入新密码：
        <Input.Password
          value={password}
          onChange={handlePasswordChange}
          placeholder="请输入新密码"
          iconRender={(visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
        />
        {password ? <>密码强度：{passwordStrength[passwordStrengthLevel]}</> : <></>}
        确认新密码：
        <Input.Password
          value={confirmPassword}
          onChange={handleConfirmPasswordChange}
          placeholder="请确认新密码"
          iconRender={(visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
        />
        <Button style={{ width: 80 }} onClick={() => ChangePassword()}>
          修改
        </Button>
      </Space>
    </>
  );
};

export default SecurityView;
