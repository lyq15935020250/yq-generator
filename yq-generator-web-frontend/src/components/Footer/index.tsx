import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = '顶级sc lyqq';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'codeNav',
          title: '前端地址',
          href: 'https://github.com/lyq15935020250/yqBI-frontend',
          blankTarget: true,
        },
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined /> yq 源码
            </>
          ),
          href: 'https://github.com/lyq15935020250/yq-generator',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
