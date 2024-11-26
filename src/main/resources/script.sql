create table account
(
    id         int auto_increment comment '用户id'
        primary key,
    username   varchar(255)  not null comment '用户姓名',
    password   varchar(255)  not null comment '用户密码',
    age        int           null comment '年龄',
    gender     varchar(10)   null comment '性别',
    idCode     varchar(255)  null comment '身份证号码',
    email      varchar(255)  null comment '电子邮箱',
    telephone  varchar(255)  null comment '电话号码',
    address    varchar(255)  null comment '住址',
    birthday   date          null comment '生日',
    department varchar(255)  null comment '部门',
    isExpert   int default 0 null comment '是否是专家'
)
    comment '用户表';

create table role
(
    id       int auto_increment comment '角色id'
        primary key,
    rolename varchar(10) default '病人' not null comment '角色名字'
)
    comment '角色表';

create table account_role
(
    uid int null comment '用户id',
    rid int null comment '角色id',
    id  int auto_increment comment 'id'
        primary key,
    constraint rid
        foreign key (rid) references role (id),
    constraint uid
        foreign key (uid) references account (id)
)
    comment '用户角色表';


