INSERT INTO profile (name) VALUES ('admin');

INSERT INTO app_user_profile (app_user_id, profile_id) VALUES ('e717b08b-e9fa-4800-ad6f-322c366ac42b', 1);

INSERT INTO business_function (application_name, function_name) VALUES ('test-app', 'view-content');

INSERT INTO permission (name) VALUES ('view');
INSERT INTO permission (name) VALUES ('edit');
INSERT INTO permission (name) VALUES ('delete');
INSERT INTO permission (name) VALUES ('create');

INSERT INTO business_function_permission(business_function_id, permission_id) VALUES (1, 1);
INSERT INTO business_function_permission(business_function_id, permission_id) VALUES (1, 2);
INSERT INTO business_function_permission(business_function_id, permission_id) VALUES (1, 3);
INSERT INTO business_function_permission(business_function_id, permission_id) VALUES (1, 4);

INSERT INTO profile_business_function_permission (business_function_permission_id, profile_id) VALUES (1, 1);
INSERT INTO profile_business_function_permission (business_function_permission_id, profile_id) VALUES (2, 1);
INSERT INTO profile_business_function_permission (business_function_permission_id, profile_id) VALUES (3, 1);
INSERT INTO profile_business_function_permission (business_function_permission_id, profile_id) VALUES (4, 1);