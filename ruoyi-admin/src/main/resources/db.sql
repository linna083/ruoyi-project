create table tb_user(
    id int(11) auto_increment primary key comment'用户编号',
    name varchar (28) null comment '姓名',
    education varchar (30) null comment '学历',
     age int null comment'年龄'
)comment '用户信息表';

create  table  book_type(
  type_id int auto_increment primary key  comment '图书类型ID',
  type_name varchar  (100)not null comment'图书类型名称'
)comment'图书类型表';

create table book(
    book_id int auto_increment primary  key comment'图书ID',
    book_name varchar (100) not null  comment '图书名称',
    book_author varchar (100) not null  comment '图书作者',
    book_image varchar (255) comment '图书封面',
    type_id int comment '图书类型ID',
    foreign key (type_id) references book_type(type_id)on delete cascade on update cascade

)comment'图书信息表';

create table department(
  dept_id int auto_increment primary  key comment '部门ID',
  dept_name varchar (100)not null comment '部门名称',
  dept_chairman varchar (100) not null  comment '部门负责人',
  parent_id int comment '上级部门ID'
)comment'部门信息表';