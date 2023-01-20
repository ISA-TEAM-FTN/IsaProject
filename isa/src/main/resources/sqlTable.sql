
CREATE TABLE Address (
                         id int unsigned AUTO_INCREMENT,
                         street varchar(256),
                         city varchar(256),
                         country varchar(256),
                         primary key (id)
);

CREATE TABLE Poll (
                      id int unsigned AUTO_INCREMENT,
                      blood_type varchar(256),
                      patient_arrived bit(1),
                      denied_type bit(1),
                      reason_for_denied bit(1),
                      amount_of_blood decimal(19,4),
                      primary key (id)
);

CREATE TABLE Loyalty_Program (
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

CREATE TABLE Center_Account(
                              id int unsigned AUTO_INCREMENT,
                              name varchar(256),
                              address_id int unsigned,
                              description varchar(256),
                              average_rating  decimal(19,4),
                              start_time time,
                              end_time time,
                              foreign key (address_id) references Address(id),
                              primary key (id)
);

CREATE TABLE User(
                     id int unsigned AUTO_INCREMENT,
                     email varchar(256),
                     password varchar(256),
                     name varchar(256),
                     surname varchar(256),
                     address_id int unsigned,
                     user_types varchar(256),
                     telephone_number varchar(256),
                     personal_id varchar(256),
                     gender varchar(256),
                     occupation varchar(256),
                     occupation_info varchar(256),
                     is_active bit(1),
                     first_login bit(1),
                     penalty_number int,
                     loyalty_program_id int unsigned,
                     center_account_id int unsigned,
                     primary key (id),
                     FOREIGN KEY (address_id) REFERENCES Address(id),
                     FOREIGN KEY (loyalty_program_id) REFERENCES Loyalty_Program(id),
                     FOREIGN KEY (center_account_id) REFERENCES Center_Account(id)
);

CREATE TABLE Appointment(
                            id int unsigned AUTO_INCREMENT,
                            center_account_id int unsigned,
                            date_and_time datetime,
                            duration int,
                            admin_of_center_id int unsigned,
                            user_id int unsigned,
                            poll_id int unsigned,
                            primary key (id),
                            FOREIGN KEY (center_account_id) REFERENCES Center_Account(id),
                            FOREIGN KEY (admin_of_center_id) REFERENCES User(id),
                            FOREIGN KEY (user_id) REFERENCES User(id),
                            FOREIGN KEY (poll_id) REFERENCES Poll(id)
);

CREATE TABLE Blood(
                      id int unsigned AUTO_INCREMENT,
                      blood_type varchar(256),
                      amount  decimal(19,4),
                      primary key (id)
);



CREATE TABLE Complaint(
                          id int unsigned AUTO_INCREMENT,
                          comment varchar(256),
                          user_id int unsigned,
                          primary key (id),
                          FOREIGN KEY(user_id) REFERENCES user(id)

);

CREATE TABLE Complaint_Answer(
                                id int unsigned AUTO_INCREMENT,
                                answer varchar(256),
                                complaint_id int unsigned,
                                primary key (id),
                                FOREIGN KEY (complaint_id) REFERENCES Complaint(id)
);

CREATE TABLE Feedback(
                         id int unsigned AUTO_INCREMENT,
                         grade int,
                         user_id int unsigned,
                         primary key (id),
                         foreign key (user_id) REFERENCES User(id)
);

CREATE TABLE QRCODE(
                       id int unsigned AUTO_INCREMENT,
                       appointment_id int unsigned,
                       qrcode_status varchar(256),
                       date_of_Creation datetime,
                       primary key (id)
);

CREATE TABLE User_Credential (
            id int unsigned AUTO_INCREMENT,
            user_id int unsigned,
            email varchar(256),
            password varchar(256),
            foreign key (user_id) REFERENCES User(id),
            primary key (id)
);

insert into `address`(street, city, country) values('Dusana Petrovica 7','Subotica','Srbija');
insert into `address`(street, city, country) values('Dusana Petrovica 71','Subotica','Srbija');

insert into Loyalty_Program(category, points) values ('REGULAR','100');

insert into Center_Account(name, address_id, description, average_rating, start_time, end_time)
values ('Center',2,'bla',6,'10:34:23','12:34:23');

insert into User(email, password, name, surname, address_id, user_types, telephone_number, personal_id, gender, occupation, occupation_info, is_active, first_login, penalty_number, loyalty_program_id, center_account_id)
values( 'nebojsa@gmail.com', '$2a$12$zMGWfImfe/HDu0/Mq68CTuwmKkiiqlQmHdpx/rhCVxPZN6wVpltTS', 'Nebojsa', 'Bogosavljev',1 ,'ADMIN_CENTER', '064495226', '264321332','MALE','student','student',true,false,0,1,1);

insert into User_Credential(user_id, email, password)
VALUES (1,'nebojsa@gmail.com','$2a$12$zMGWfImfe/HDu0/Mq68CTuwmKkiiqlQmHdpx/rhCVxPZN6wVpltTS');