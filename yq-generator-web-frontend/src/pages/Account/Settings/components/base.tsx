import {getLoginUserUsingGet, updateMyUserUsingPost, } from '@/services/backend/userController';
import { ProForm, ProFormText, ProFormTextArea } from '@ant-design/pro-components';
import { Alert, Form, message } from 'antd';
import React, { useEffect, useState } from 'react';
import useStyles from './index.style';
import AvatarUploader from '@/components/AvatarUploader';

const BaseView: React.FC = () => {
  const [form] = Form.useForm();
  const { styles } = useStyles();
  const [loading, setLoading] = useState<boolean>(true);
  const [currentUser, setCurrentUser] = useState<API.LoginUserVO>();
  const getLoginUserInfo = async () => {
    setLoading(true);
    const res = await getLoginUserUsingGet();
    setCurrentUser(res.data);
    setLoading(false);
  };

  useEffect(() => {
    getLoginUserInfo();
  }, []);
  // 头像组件 方便以后独立，增加裁剪之类的功能
  // const AvatarView = ({}: { avatar: string }) => (
  //   <>
  //     <div className={styles.avatar_title}>头像</div>
  //     <div className={styles.avatar}>
  //       <img src={currentUser?.userAvatar} alt="" />
  //     </div>
  //     <Upload showUploadList={false}>
  //       <div className={styles.button_view}>
  //         <Button>
  //           <UploadOutlined />
  //           更换头像
  //         </Button>
  //       </div>
  //     </Upload>
  //   </>
  // );
  // AvatarView组件
  const AvatarView = ({}: { onAvatarChange: (newAvatarUrl: string) => void }) => {
    const handleChange = async (newAvatarUrl: string) => {
      // 更新表单中头像字段的值
      form.setFieldsValue({ userAvatar: newAvatarUrl });

      // 如果需要同步更新currentUser对象中的头像地址
      setCurrentUser((prevState) => ({ ...prevState, userAvatar: newAvatarUrl }));
    };

    return (
      <>
        <div className={styles.avatar_title}>头像</div>
        {/*<div className={styles.avatar}>*/}
        {/*  <img src={currentUser?.userAvatar} alt="" />*/}
        {/*</div>*/}
        <Alert
          className={styles.userAvatar_title}
          message="点击图片更换头像"
          type="warning"
          closable
        />
        <AvatarUploader
          biz="user_avatar" // 传入biz参数
          value={currentUser?.userAvatar}
          onChange={handleChange} // 添加onChange回调
        />
      </>
    );
  };
  // const getAvatarURL = () => {
  //   if (currentUser) {
  //     if (currentUser.userAvatar) {
  //       return currentUser.userAvatar;
  //     }
  //     const url = 'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png';
  //     return url;
  //   }
  //   return '';
  // };

  const handleChangeAvatar = async (newAvatarUrl: string) => {
    // 更新表单中 userAvatar 字段的值
    form.setFieldsValue({ userAvatar: newAvatarUrl });

    // 同步更新 currentUser 对象中的头像地址
    setCurrentUser((prevState) => ({
      ...prevState,
      userAvatar: newAvatarUrl,
    }));

    // 如果你想立即保存更改到后端（实时保存）
    // 可以在这里发起一个异步请求来更新用户头像
    try {
      const updateResponse = await updateMyUserUsingPost({
        userName: currentUser?.userName,
        userAvatar: newAvatarUrl,
      });

      if (updateResponse && updateResponse.code === 0) {
        message.success('头像更新成功');
      } else {
        message.error('头像更新失败: ' + (updateResponse.message || '未知错误'));
      }
    } catch (error) {
      console.error('更新头像时发生错误:', error);
      message.error('头像更新过程中发生了错误，请稍后再试');
    }
  };

  const onFinish = async (values: API.UserUpdateMyRequest) => {
    // 创建一个临时变量，从currentUser中提取所需属性，并合并用户新输入的值

    const updateData: API.UserUpdateMyRequest = {
      userAvatar: currentUser?.userAvatar,
      userName: values?.userName,
      userProfile: values?.userProfile,
    };
    console.log('updateData:', updateData);
    const response = await updateMyUserUsingPost(updateData);
    if (response && response.code === 0) {

      message.success('更新用户信息成功', {
        // 设置消息持续时间，单位为毫秒
        // @ts-ignore
        duration: 1500,  // 例如，设定为1.5秒
      });

      // 使用 setTimeout 实现在消息消失后刷新页面
      setTimeout(() => {
        window.location.reload();
      }, 1500);  // 与上面消息持续时间保持一致

    } else {
      message.error('更新用户信息失败: ' + (response.message || '未知错误'));
    }
  };
  return (
    <div className={styles.baseView}>
      {loading ? null : (
        <>
          <div className={styles.left}>
            <ProForm
              form={form}
              layout="vertical"
              onFinish={onFinish}
              submitter={{
                searchConfig: {
                  submitText: '更新基本信息',
                },
                render: (_, dom) => dom[1],
              }}
              hideRequiredMark
            >
              <ProFormText
                width="md"
                name="userName"
                label="昵称"
                initialValue={currentUser?.userName}
                rules={[
                  {
                    required: true,
                    message: '请输入您的昵称!',
                  },
                ]}
              />
              <ProFormTextArea
                name="userProfile"
                label="个人简介"
                initialValue={currentUser?.userProfile}
                rules={[
                  {
                    required: true,
                    message: '请输入个人简介!',
                  },
                ]}
                placeholder="个人简介"
              />
            </ProForm>
          </div>
          {/*<div className={styles.right}>*/}
          {/*  <AvatarView avatar={getAvatarURL()} />*/}
          {/*</div>*/}
          <div className={styles.right}>
            <AvatarView onAvatarChange={handleChangeAvatar} />
          </div>
        </>
      )}
    </div>
  );
};
export default BaseView;
