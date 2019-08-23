drop database if exists braylon;

create database braylon;

use braylon;

create table Customer (
	customerId int primary key auto_increment,
    customerFirstName varchar(50) not null,
    customerLastName varchar(50) not null,
    address varchar(50) not null,
    phone varchar(50) not null,
    companyName varchar(50) not null,
    email varchar(50) not null
);
insert into Customer values (1,'Nick', 'Vincent', '123 Main St','123-123-1234','Genesis12','asf@asf.com'),(2,'Rob', 'Stark', '12 Wintefell Lane','234-123-1245','House Stark','kingof@north.imdeadspoilers');

create table `User` (
	userId int primary key auto_increment,
    username varchar(50) not null,
    email varchar(50) not null,
    password varchar(1000) not null,
    enabled boolean not null,
    firstName varchar(50) not null,
    lastName varchar(50) not null
);
insert into User values (1,'Andrew','andrew@yahoomail.hotmail.com','$2a$10$IuKgHhWowwrQt9i5IgxB6uU.3Mr5l32DiRCbddTfP1vptAnW5BwWe',1,'Andrew','Larson'),(2,'BigAl','albertLarge@gmail.big','$2a$10$IuKgHhWowwrQt9i5IgxB6uU.3Mr5l32DiRCbddTfP1vptAnW5BwWe',1,'Big','Boy'),(3,'Daveson','44lexingtonparkway@address.com','$2a$10$IuKgHhWowwrQt9i5IgxB6uU.3Mr5l32DiRCbddTfP1vptAnW5BwWe',1,'Dave','SonOfDave');
create table `Order` (
	orderId int primary key auto_increment,
    customerId int not null,
    orderDate date not null,
    fulfillmentDate date,
    status varchar(50) not null,
    price decimal not null,
    userId int not null,
    foreign key (userId) references User(userId),
    foreign key (customerId) references Customer(customerId)
);
insert into `Order` values (1,1,'2020-02-12','2020-03-12','pending','20123.12','1'),(2,2,'2020-02-12','2020-03-12','pending','1234.12','2'),(3,1,'2020-02-12','2020-03-12','pending','5342.12','2'),(4,2,'2020-02-12','2020-03-12','pending','2123.12','3');
create table `Role`(
	roleId int primary key auto_increment,
	role varchar(50) not null
);
insert into `Role` values (1,'Admin'),(2,'SalesRep'),(3,'Executive');
#bridge table 
create table `userRole`(
	userId int not null,
	roleId int not null,
	primary key(userId,roleId),
	foreign key (userId) references User(userId),
	foreign key (roleId) references Role(roleId)
);
insert into userRole values (1,2),(2,2),(3,3);
create table `Product` (
	productId int primary key auto_increment,
    productName varchar(50) not null,
    inventory int not null,
    price decimal not null
);
insert into `Product` values (1,'Screen',23,1234.12),(2,'Camera',53,4534.12),(3,'Light',233,123.12);
create table orderProduct(
	orderId int not null,
    productId int not null,
    foreign key (orderId) references `Order`(orderId),
    foreign key (productId) references Product(productId)
);

insert into orderProduct values (1,1),(1,2),(1,3),(2,1),(2,3),(3,1),(4,1),(4,2);
create table SalesVisit (
	salesvisitId int primary key auto_increment,
    visitDate date not null,
    customerId int not null,
    userId int not null,
    location varchar(50) not null,
    notes varchar(450) not null,
    orderId int,
    foreign key (userId) references User(userId),
    foreign key (customerId) references Customer(customerId),
    foreign key (orderId) references `Order`(orderId)
);
insert into SalesVisit values (1,'2020-02-12',2,1,'Home','ahhh','1'),(2,'2020-02-14',1,2,'Company','ahhh','2'),(3,'2020-02-16',2,3,'Home','ahhh','3'),(4,'2020-02-17',1,2,'Home','ahhh','4'),(5,'2020-02-18',2,1,'AHOIASFH','ahhh','1');
select * from Customer;
select * from User;
Select * from User u where username like "Andrew";
select * from SalesVisit;
SELECT c.*, s.userId FROM User u JOIN SalesVisit s ON s.customerId = u.userId JOIN Customer c ON s.customerID = c.customerID WHERE u.userId = 1;