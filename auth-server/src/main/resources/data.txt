INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('web', '{bcrypt}$2a$10$NzvQzN5149Xy2jY9b8u3W.PTF7q1Mdfkq7dNgcGTyz5I8EfQF0JsW', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

 INSERT INTO permission (NAME) VALUES
 ('create_profile'),
 ('read_profile'),
 ('update_profile'),
 ('delete_profile');

 INSERT INTO role (NAME) VALUES
		('ROLE_admin'),('ROLE_operator');

 INSERT INTO permission_role (PERMISSION_ID, ROLE_ID) VALUES
     (1,1), /*create-> admin */
     (2,1), /* read admin */
     (3,1), /* update admin */
     (4,1), /* delete admin */
     (2,2),  /* read operator */
     (3,2);  /* update operator */
insert into user ( user_name,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ( 'tharindu','{bcrypt}$2a$10$b4tfK833EJxE9P3KOtes9O8J91SJ7bJrf53C4MaESaKbloR4GVwB6', 'tharindu@gmail.com', '1', '1', '1', '1');
 insert into  user ( user_name,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ( 'lakshan', '{bcrypt}$2a$10$7mDcebFokIV1oH7oXhRF7Oo3PMNcB3UyZ0EHgrVcQQqw/VTOQ.5GS','lakshan@gmail.com', '1', '0', '0', '0');

INSERT INTO role_user (ROLE_ID, USER_ID)
    VALUES
    (1, 1) /* tharindu-admin */,
    (2, 2) /* lakshan-operator */ ;