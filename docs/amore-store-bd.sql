create table categories(
    id integer primary key AUTO_INCREMENT,
    name varchar(50) not null unique,
    description varchar(256) null,
    status bit default(1)
);

create table products(
    id integer primary key AUTO_INCREMENT,
    category_id integer not null,
    reference_number varchar(50) null,
    name varchar(100) not null unique,
    price decimal(11,2) not null,
    stock integer not null,
    description varchar(256) null,
    status bit default(1)
);


ALTER TABLE products ADD FOREIGN KEY (category_id) REFERENCES categories(id);

create table roles(
    id integer primary key AUTO_INCREMENT,
    name varchar(30) not null,
    description varchar(100) null,
    status bit default(1)
);


create table users(
    id integer primary key AUTO_INCREMENT,
    role_id integer not null,
    name varchar(100) not null,
    document_type varchar(20) null,
    document_number varchar(20) null,
    addres varchar(70) null,
    phone varchar(20) null,
    email varchar(50) not null,
    password varchar(50) not null,
    status bit default(1)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);

create table sales(
       id integer primary key AUTO_INCREMENT,
       user_id integer not null,
       voucher_number varchar (10) not null,
       date_time datetime not null,
       iva decimal (4,2) not null,
       total decimal (11,2) not null,
       status bit default(1)
);

ALTER TABLE sales ADD FOREIGN KEY (user_id) REFERENCES users (id);


create table sale_details(
       id integer primary key AUTO_INCREMENT,
       sale_id integer not null,
       product_id integer not null,
       amount integer not null,
       price decimal(11,2) not null,
       discount decimal(11,2) not null
);
       
ALTER TABLE sale_details ADD FOREIGN KEY (sale_id) REFERENCES sales (id) ON DELETE CASCADE;

ALTER TABLE sale_details ADD FOREIGN KEY (product_id) REFERENCES products (id);


------ INSERT DATA ------
INSERT INTO `categories`(`name`, `description`, `status`) VALUES ('Lenceria','Lenceria',1);


INSERT INTO `products`(`category_id`, `reference_number`, `name`, `price`, `stock`, `description`, `status`) 
VALUES (1,'1235468798','Lenceria roja',20000,22,'Lenceria roja talla unica',1);  