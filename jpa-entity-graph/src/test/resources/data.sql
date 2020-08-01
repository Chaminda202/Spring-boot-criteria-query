INSERT INTO user VALUES (1, 'example1@wanari.com', 'John', 'Doe');
INSERT INTO user VALUES (2, 'example2@wanari.com', 'Jude', 'Cox');
INSERT INTO user VALUES (3, 'example3@wanari.com', 'Logan', 'Brooks');
INSERT INTO user VALUES (4, 'example4@wanari.com', 'Reece', 'Holmes');
INSERT INTO user VALUES (5, 'example5@wanari.com', 'Billy', 'Cook');

INSERT INTO address (id, user_id, zip, street, city) VALUES (1, 1, 1061, 'Paladino Gardens Northwest', 'Budapest');
INSERT INTO address (id, user_id, zip, street, city) VALUES (2, 1, 5423, 'East Sturbridge Garth', 'Budapest');
INSERT INTO address (id, user_id, zip, street, city) VALUES (3, 1, 3426, 'North Hycrest Walk', 'Budapest');
INSERT INTO address (id, user_id, zip, street, city) VALUES (4, 2, 5363, 'Little Lever', 'Budapest');
INSERT INTO address (id, user_id, zip, street, city) VALUES (5, 2, 2345, 'Demers Street', 'London');
INSERT INTO address (id, user_id, zip, street, city) VALUES (6, 2, 7786, 'South Skenes Street', 'London');
INSERT INTO address (id, user_id, zip, street, city) VALUES (7, 2, 8977, 'Woodvale Green', 'Budapest');
INSERT INTO address (id, user_id, zip, street, city) VALUES (8, 3, 7897, 'Crimmins Mews', 'Wien');
INSERT INTO address (id, user_id, zip, street, city) VALUES (9, 5, 7878, 'Turuga Bypass', 'Salt Lake City');
INSERT INTO address (id, user_id, zip, street, city) VALUES (10, 5, 8957, 'South Strawberry Canyon Garth', 'Sidney');
INSERT INTO address (id, user_id, zip, street, city) VALUES (11, 5, 5685, 'White Oak', 'Budapest');
INSERT INTO address (id, user_id, zip, street, city) VALUES (12, 5, 2346, 'East Summer Heights', 'Budapest');


INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (1, '+94717345623', 'MOBILE', 0, 1);
INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (2, '+94113456789', 'LAND_LINE', 0, 1);
INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (3, '+94777345456', 'MOBILE', 0, 2);
INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (4, '+94774563455', 'MOBILE', 0, 2);
INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (5, '+94777344568', 'MOBILE', 0, 3);
INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (6, '+94777341234', 'MOBILE', 0, 4);
INSERT INTO phone (id, phone_number, phone_type, version, user_id) VALUES (7, '+94713243567', 'MOBILE', 0, 5);

INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (1, '2020-08-01 00:10:58.078000', 125, 0, 1);
INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (2, '2020-08-01 00:10:58.078000', 189, 0, 1);
INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (3, '2020-08-01 00:10:58.078000', 340, 0, 2);
INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (4, '2020-08-01 00:10:58.078000', 503, 0, 3);
INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (5, '2020-08-01 00:11:05.078000', 345, 0, 3);
INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (7, '2020-08-01 00:11:58.078000', 246, 0, 4);
INSERT INTO calls (id, call_date, duration_seconds, version, phone_id) VALUES (8, '2020-08-01 00:13:58.078000', 560, 0, 5);