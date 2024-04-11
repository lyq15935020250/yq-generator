import useStyles from '@/pages/Account/Center/Center.style';
import { listMyGeneratorVoByPageUsingPost } from '@/services/backend/generatorController';
import { getLoginUserUsingGet } from '@/services/backend/userController';
import { Link } from '@@/exports';
import { GridContent } from '@ant-design/pro-components';
import { Card, Col, Divider, Flex, Image, List, Row, Tag, Typography } from 'antd';
import React, { useEffect, useState } from 'react';

const operationTabList = [
  {
    key: 'articles',
    tab: (
      <span>
        我的生成器{' '}
        <span
          style={{
            fontSize: 14,
          }}
        ></span>
      </span>
    ),
  },
];
const Center: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(true);
  const [dataList, setDataList] = useState<API.GeneratorVO[]>([]);
  const [total, setTotal] = useState<number>(0);
  const { styles } = useStyles();
  // const [tabKey, setTabKey] = useState<tabKeyType>('articles');
  const [currentUser, setCurrentUser] = useState<API.LoginUserVO>();

  const getLoginUserInfo = async () => {
    const res = await getLoginUserUsingGet();
    setCurrentUser(res.data);
  };

  const getGeneratorList = async () => {
    setLoading(true);

    const res = await listMyGeneratorVoByPageUsingPost({});
    setDataList(res.data?.records ?? []);
    setTotal(Number(res.data?.total) ?? 0);
    console.log(res);
    console.log(res.data?.records);

    setLoading(false);
  };

  useEffect(() => {
    getLoginUserInfo();
    getGeneratorList();
  }, []);

  // 渲染标签
  const tagListView = (tags?: string[]) => {
    if (!tags) {
      return <></>;
    }

    return (
      <div style={{ marginBottom: 8 }}>
        {tags.map((tag) => (
          <Tag key={tag}>{tag}</Tag>
        ))}
      </div>
    );
  };

  //  渲染用户信息

  // 渲染tab切换
  // const renderChildrenByTabKey = (tabValue: tabKeyType) => {
  //   if (tabValue === 'projects') {
  //     return <Projects />;
  //   }
  //   if (tabValue === 'applications') {
  //     return <Applications />;
  //   }
  //   if (tabValue === 'articles') {
  //     return <Articles />;
  //   }
  //   return null;
  // };
  return (
    <GridContent>
      <Row gutter={24}>
        <Col lg={7} md={24}>
          <Card
            bordered={false}
            style={{
              marginBottom: 24,
            }}
            loading={loading}
          >
            {!loading && currentUser && (
              <div>
                <div className={styles.avatarHolder}>
                  <img alt="" src={currentUser.userAvatar} />
                  <div className={styles.name}>{currentUser.userName}</div>
                  <div>我的身份：{currentUser.userRole === 'admin' ? '管理员' : '普通用户'}</div>
                  <div>个人介绍：{currentUser.userProfile}</div>
                </div>
                <Divider dashed />
                <Divider
                  style={{
                    marginTop: 16,
                  }}
                  dashed
                />
              </div>
            )}
          </Card>
        </Col>
        <Col lg={17} md={24}>
          <Card
            className={styles.tabsCard}
            bordered={false}
            tabList={operationTabList}
            // activeTabKey={tabKey}
            /*onTabChange={(_tabKey: string) => {
              setTabKey(_tabKey as tabKeyType);
            }}*/
          >
            <List<API.GeneratorVO>
              rowKey="id"
              loading={loading}
              grid={{
                gutter: 16,
                xs: 1,
                sm: 2,
                md: 3,
                lg: 3,
                xl: 4,
                xxl: 4,
              }}
              dataSource={dataList}
              renderItem={(data) => (
                <List.Item>
                  <Link to={`/generator/detail/${data.id}`}>
                    <Card hoverable cover={<Image alt={data.name} src={data.picture} />}>
                      <Card.Meta
                        title={<a>{data.name}</a>}
                        description={
                          <Typography.Paragraph ellipsis={{ rows: 2 }} style={{ height: 44 }}>
                            {data.description}
                          </Typography.Paragraph>
                        }
                      />
                      {tagListView(data.tags)}
                      <Flex justify="space-between" align="center">
                        {/*<Typography.Text type="secondary" style={{ fontSize: 12 }}>*/}
                        {/*  {moment(data.createTime).fromNow()}*/}
                        {/*</Typography.Text>*/}
                        {/*<div>*/}
                        {/*  <Avatar src={data.user?.userAvatar ?? <UserOutlined />} />*/}
                        {/*</div>*/}
                      </Flex>
                    </Card>
                  </Link>
                </List.Item>
              )}
            />
          </Card>
        </Col>
      </Row>
    </GridContent>
  );
};
export default Center;
