CREATE TABLE Address (
                         id int unsigned,
                         street varchar(256),
                         city varchar(256),
                         country varchar(256),
                         primary key (id)
);

CREATE TABLE Poll (
                      id int unsigned,
                      bloodType varchar(256),
                      patientArrived bit(1),
                      deniedType bit(1),
                      reasonForDenied bit(1),
                      amountOfBlood decimal(19,4),
                      primary key (id)
);

CREATE TABLE LoyaltyProgram (
                                id int unsigned,
                                category varchar(256),
                                points int,
                                primary key (id)
);

CREATE TABLE Equipment (
                           id int unsigned,
                           amount int,
                           name varchar(256),
                           primary key (id)
);

CREATE TABLE CenterAccount(
                              id int unsigned,
                              name varchar(256),
                              address varchar(256),
                              description varchar(256),
                              averageRating  decimal(19,4),
                              startTime time,
                              endTime time,
                              primary key (id)
);

CREATE TABLE User(
                     id int unsigned,
                     email varchar(256),
                     password varchar(256),
                     name varchar(256),
                     surname varchar(256),
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
                     FOREIGN KEY (loyaltyProgramId) REFERENCES LoyaltyProgram(id),
                     FOREIGN KEY (centerAccountID) REFERENCES CenterAccount(id)
);

CREATE TABLE Appointment(
                            id int unsigned,
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
                      id int unsigned,
                      bloodType varchar(256),
                      amount  decimal(19,4),
                      primary key (id)
);



CREATE TABLE Complaint(
                          id int unsigned,
                          comment varchar(256),
                          userId int unsigned,
                          primary key (id),
                          FOREIGN KEY(userId) REFERENCES user(id)

);

CREATE TABLE ComplaintAnswer(
                                id int unsigned,
                                answer varchar(256),
                                complaintId int unsigned,
                                primary key (id),
                                FOREIGN KEY (complaintId) REFERENCES Complaint(id)
);

CREATE TABLE Feedback(
                         id int unsigned,
                         grade int,
                         userId int unsigned,
                         primary key (id),
                         foreign key (userId) REFERENCES User(id)
);

CREATE TABLE QRCode(
                       id int unsigned,
                       appointmentId int unsigned,
                       qrCodeStatus varchar(256),
                       dateOfCreation datetime,
                       primary key (id)
);