# 这是我的个人毕设项目 ——基于SpringBoot+Mybatis的航海学院羽毛球社团网站

## 系统设计

## E-R图

![](https://github.com/819894914/Badminton-Club/blob/master/src/main/resources/static/images/E-R%E5%9B%BE-2.drawio.png?raw=true)

其中，游客、管理员与社团成员的属性相同，游客、管理员即该实体的身份。

## 系统结构图

![](https://github.com/819894914/Badminton-Club/blob/master/src/main/resources/static/images/%E7%B3%BB%E7%BB%9F%E5%8A%9F%E8%83%BD%E7%BB%93%E6%9E%84%E5%9B%BE.drawio.png?raw=true)

## 系统用例图

![](https://github.com/819894914/Badminton-Club/blob/master/src/main/resources/static/images/%E7%B3%BB%E7%BB%9F%E7%94%A8%E4%BE%8B%E5%9B%BE.drawio.png?raw=true)

## 数据字典

表 member  用户表

| 字段名          | 数据类型    | 是否允许空 | 字段含义     |
| --------------- | ----------- | ---------- | ------------ |
| member_id       | int(11)     | NOT NULL   | 用户ID       |
| member_name     | varchar(4)  | NOT NULL   | 用户姓名     |
| member_sex      | varchar(1)  | NOT NULL   | 用户性别     |
| member_grade    | varchar(2)  | NOT NULL   | 用户年级     |
| member_identity | varchar(3)  | NOT NULL   | 用户身份     |
| member_phone    | varchar(11) | NULL       | 用户号码     |
| member_account  | varchar(10) | NOT NULL   | 用户账号     |
| member_password | varchar(8)  | NOT NULL   | 用户密码     |
| member_ismember | tinyint(1)  | NOT NULL   | 是否社团成员 |



表 messages 文章表

| 字段名               | 数据类型     | 是否允许空 | 字段含义 |
| -------------------- | ------------ | ---------- | -------- |
| messages_id          | int(11)      | NOT NULL   | 文章ID   |
| messages_title       | varchar(255) | NULL       | 文章标题 |
| messages_content     | longtext     | NULL       | 文章内容 |
| messages_releasedate | varchar(10)  | NULL       | 发布日期 |
| messages_kind        | varchar(2)   | NOT NULL   | 文章种类 |



表 board 留言板表

| 字段名            | 数据类型     | 是否允许空 | 字段含义 |
| ----------------- | ------------ | ---------- | -------- |
| board_id          | int(11)      | NOT NULL   | 留言ID   |
| board_content     | varchar(255) | NOT NULL   | 留言内容 |
| board_releasedate | varchar(10)  | NOT NULL   | 发布日期 |
| member_id         | longtext     | NOT NULL   | 留言人   |



表 admissionapplication 入社申请表

| 字段名                  | 数据类型 | 是否允许空 | 字段含义 |
| ----------------------- | -------- | ---------- | -------- |
| admissionapplication_id | int(11)  | NOT NULL   | 审核ID   |
| member_id               | int(11)  | NOT NULL   | 用户ID   |
| status                  | int(1)   | NOT NULL   | 审核状态 |

## 实现效果

网站前台页面

![](https://github.com/819894914/Badminton-Club/blob/master/src/main/resources/static/images/image-20220520144221661.png?raw=true)

网站后台管理页面

![](https://github.com/819894914/Badminton-Club/blob/master/src/main/resources/static/images/image-20220520144244528.png?raw=true)