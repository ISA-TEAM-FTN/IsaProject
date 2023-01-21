INSERT INTO ADDRESS (city, country, street) VALUES ('Subotica','Srbija','Neka adresa');
INSERT INTO ADDRESS (city, country, street) VALUES ('Subotica','Srbija','Dusana Petrovica 7');

insert into loyalty_program(category, points) values ('REGULAR','100');

insert into Center_Account(name, address_id, description, average_rating, start_time, end_time) values ('Center',2,'bla',6,'10:34:23','12:34:23');

INSERT INTO USERS (email, password, first_name, last_name, enabled, last_password_reset_date, gender, first_login, occupation_info, occupation, penalty_number, telephone_number, is_active,personal_id,address_id,loyalty_program_id,center_account_id) VALUES ('nebojsa@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nebojsa', 'Bogosavljev', true, '2017-10-01 21:58:58.508-07','MALE',false,'student','yes',0,'01231',true,'3213',1,null,1);
INSERT INTO USERS (email,password, first_name, last_name, enabled, last_password_reset_date, gender, first_login, occupation_info, occupation, penalty_number, telephone_number, is_active,personal_id,address_id,loyalty_program_id,center_account_id) VALUES ('admin@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nebojsa', 'Bogosavljev', true, '2017-10-01 18:57:58.508-07','MALE',false,'admin','admin info',0,'031231',true,'32131',2,null,null);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN_SYSTEM');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN_CENTER');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
