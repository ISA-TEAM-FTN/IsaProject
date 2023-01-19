
CREATE TABLE Address (
                         id int unsigned AUTO_INCREMENT,
                         street varchar(256),
                         city varchar(256),
                         country varchar(256),
                         primary key (id)
);

CREATE TABLE Poll (
                      id int unsigned AUTO_INCREMENT,
                      bloodType varchar(256),
                      patientArrived bit(1),
                      deniedType bit(1),
                      reasonForDenied bit(1),
                      amountOfBlood decimal(19,4),
                      primary key (id)
);

CREATE TABLE LoyaltyProgram (
                                id int unsigned AUTO_INCREMENT,
                                category varchar(256),
                                points int,
                                primary key (id)
);

CREATE TABLE Equipment (
                           id int unsigned AUTO_INCREMENT,
                           amount int,
                           name varchar(256),
                           primary key (id)
);

CREATE TABLE CenterAccount(
                              id int unsigned AUTO_INCREMENT,
                              name varchar(256),
                              addressId int unsigned,
                              description varchar(256),
                              averageRating  decimal(19,4),
                              startTime time,
                              endTime time,
                              foreign key (addressId) references Address(id),
                              primary key (id)
);

CREATE TABLE User(
                     id int unsigned AUTO_INCREMENT,
                     email varchar(256),
                     password varchar(256),
                     name varchar(256),
                     surname varchar(256),
                     addressId int unsigned,
                     userTypes varchar(256),
                     telephoneNumber varchar(256),
                     personalId varchar(256),
                     gender varchar(256),
                     occupation varchar(256),
                     occupationInfo varchar(256),
                     isActive bit(1),
                     firstLogin bit(1),
                     penaltyNumber int,
                     loyaltyProgramId int unsigned,
                     centerAccountID int unsigned,
                     primary key (id),
                     FOREIGN KEY (addressId) REFERENCES Address(id),
                     FOREIGN KEY (loyaltyProgramId) REFERENCES LoyaltyProgram(id),
                     FOREIGN KEY (centerAccountID) REFERENCES CenterAccount(id)
);

CREATE TABLE Appointment(
                            id int unsigned AUTO_INCREMENT,
                            centerAccountId int unsigned,
                            dateAndTime datetime,
                            duration int,
                            adminOfCenterId int unsigned,
                            patientId int unsigned,
                            pollId int unsigned,
                            primary key (id),
                            FOREIGN KEY (centerAccountId) REFERENCES CenterAccount(id),
                            FOREIGN KEY (adminOfCenterId) REFERENCES User(id),
                            FOREIGN KEY (patientId) REFERENCES User(id),
                            FOREIGN KEY (pollId) REFERENCES Poll(id)
);

CREATE TABLE Blood(
                      id int unsigned AUTO_INCREMENT,
                      bloodType varchar(256),
                      amount  decimal(19,4),
                      primary key (id)
);



CREATE TABLE Complaint(
                          id int unsigned AUTO_INCREMENT,
                          comment varchar(256),
                          userId int unsigned,
                          primary key (id),
                          FOREIGN KEY(userId) REFERENCES user(id)

);

CREATE TABLE ComplaintAnswer(
                                id int unsigned AUTO_INCREMENT,
                                answer varchar(256),
                                complaintId int unsigned,
                                primary key (id),
                                FOREIGN KEY (complaintId) REFERENCES Complaint(id)
);

CREATE TABLE Feedback(
                         id int unsigned AUTO_INCREMENT,
                         grade int,
                         userId int unsigned,
                         primary key (id),
                         foreign key (userId) REFERENCES User(id)
);

CREATE TABLE QRCode(
                       id int unsigned AUTO_INCREMENT,
                       appointmentId int unsigned,
                       qrCodeStatus varchar(256),
                       dateOfCreation datetime,
                       primary key (id)
);

CREATE TABLE UserCredential (
            id int unsigned AUTO_INCREMENT,
            userId int unsigned,
            email varchar(256),
            password varchar(256),
            foreign key (userId) REFERENCES User(id),
            primary key (id)
);

insert into `address`(street, city, country) values('Dusana Petrovica 7','Subotica','Srbija');
insert into `address`(street, city, country) values('Dusana Petrovica 71','Subotica','Srbija');

insert into LoyaltyProgram(category, points) values ('REGULAR','100');

insert into CenterAccount(name, addressId, description, averageRating, startTime, endTime)
values ('Center',2,'bla',6,'10:34:23','12:34:23');

insert into `user` ( `email`, `password`, `name`, `surname`, `userTypes`, `addressId`, `telephoneNumber`, `personalId`, `gender`, `occupation`, `occupationInfo`, `isActive`,`firstLogin`,`penaltyNumber`,`loyaltyProgramId`,`centerAccountID`)
values( 'nebojsa@gmail.com', '$2a$12$zMGWfImfe/HDu0/Mq68CTuwmKkiiqlQmHdpx/rhCVxPZN6wVpltTS', 'Nebojsa', 'Bogosavljev', 'ADMIN_SYSTEM', 1, '064495226', '264321332','MALE','student','student',true,false,0,1,1);

insert into `user_credential`(email, password, user_id)
VALUES ('nebojsa@gmail.com','$2a$12$zMGWfImfe/HDu0/Mq68CTuwmKkiiqlQmHdpx/rhCVxPZN6wVpltTS',1);