INSERT INTO  person(person_type, svnr, dateofbirth,
                    driverlicencenumber, firstname, lastname,
                    phonenumber)
VALUES ('Mech', '1234567890', '2002-02-02','AT-123-445', 'Max', 'Mechaniker','028457472812'),
       ('Mech', '9999999999', '2002-03-03','AT-144-495', 'Lina', 'Lila','95786893092'),
       ('Mech','8888888888', '1969-12-12','AT-187-420','Werner', 'Kaiser','06601234567'),
       ('Mech','7777777777', '1999-09-09','AT-420-187','Filliepp', 'Gerschpaumm','06607654321'),
       ('Mech','6666666666', '1978-11-11','AT-333-666','Christoph', 'Handel','06604206999'),
       ('Own','5555555555', '1979-01-02','AT-111-111','Quarkus', 'Rempl','06601111111'),
       ('Own','4444444444', '1980-02-03','AT-222-222','Nico', 'Hirsch','06602222222'),
       ('Own','3333333333', '1981-03-04','AT-333-333','Michael', 'Tran','06603333333'),
       ('Own','2222222222', '1982-04-05','AT-444-444','Florian', 'Keintzel','06604444444'),
       ('Own','1111111111', '1983-05-06','AT-555-555','Oliver', 'Sugic','06605555555'),
       ('Ten','1111122222', '1984-06-07','AT-666-666','Hansi', 'Tunc','06601112222'),
       ('Ten','1111133333', '1985-07-08','AT-777-777','Sebatijan', 'Bagdan','06601113333'),
       ('Ten','1111144444', '1986-08-09','AT-888-888','Edina', 'Abasovic','06601114444'),
       ('Ten','1111155555', '1987-09-10','AT-999-999','Paul', 'Binder','06601115555'),
       ('Ten','1111166666', '1988-10-11','AT-832-432','Niklas', 'Neudorfer','06601116666');

INSERT INTO mechanic(priceperhour, workend, workstart, person_id)
VALUES (20, '12:00', '08:00', '1234567890'),
       (45.4, '17:00', '12:00', '9999999999'),
       (50.1, '04:00', '20:00', '8888888888'),
       (51.2, '05:00', '21:00', '7777777777'),
       (52.2, '06:00', '22:00', '6666666666');

INSERT INTO owner(person_id)
VALUES (5555555555),
       (4444444444),
       (3333333333),
       (2222222222),
       (1111111111);

INSERT INTO tenant(pricediscountpercent, person_id)
VALUES (11.1,'1111122222'),
       (22.2,'1111133333'),
       (33.3,'1111144444'),
       (44.4,'1111155555'),
       (55.5,'1111166666');

INSERT into vehicle(acceleration, brand, constructionperyear, horsepower, owner_person_id)
VALUES (100, 'BMW', '2022-12-30', 500, null),
       (95, 'Audi', '2021-11-29', 180, null),
       (90, 'VW', '2020-10-28', 150, null),
       (85, 'Mercedes', '2019-09-27', 200, null),
       (80, 'Mustang', '2018-08-26', 360, null);

INSERT INTO reparation(duration, nextappointment, mechanic_person_id, vehicle_id)
VALUES (2, datetime_pl('2022-12-30', '23:00'), '1234567890', 1),
       (2, datetime_pl('2022-11-29', '22:00'), '9999999999', 2),
       (2, datetime_pl('2022-10-28', '21:00'), '8888888888', 3),
       (2, datetime_pl('2022-09-27', '20:00'), '7777777777', 4),
       (2, datetime_pl('2022-08-26', '19:00'), '9999999999', 1);

INSERT INTO part(description, parttype, amountstored)
VALUES ('Continental M&S 275-17R-55', 'Bremsklotz', 10),
       ('Pirelli Scorpion Verde', 'Reifen', 5),
       ('Pirelli M&S ', 'Bremsscheibe', 7),
       ('Scorpion M&S 55', 'Reifen', 3),
       ('Verde Grabber', 'Scheibe', 20);

INSERT INTO replacement(amount, reparation_id, part_description, part_parttype)
VALUES (1,1,'Pirelli Scorpion Verde','Reifen'),
       (2,2,'Continental M&S 275-17R-55', 'Bremsklotz'),
       (3,3,'Scorpion M&S 55', 'Reifen'),
       (1,4,'Verde Grabber', 'Scheibe'),
       (2,5,'Pirelli M&S ', 'Bremsscheibe');

insert into vehicletransfer(time, newowner_svnr, vehicle_id)
VALUES ('2022-01-30 12:12:00.000',5555555555,1),
       ('2022-02-01 13:13:00.000',4444444444,2),
       ('2022-02-02 14:14:00.000',3333333333,3),
       ('2022-02-03 15:15:00.000',4444444444,4),
       ('2022-02-04 16:16:00.000',4444444444,1);

insert into rental(r_from, r_to, tenant_person_id, vehicle_id)
VALUES ('2022-01-26 02:15:00.000','2022-01-27 02:15:00.000',1111122222,1),
       ('2022-01-27 02:30:00.000','2022-01-28 02:30:00.000',1111133333,2),
       ('2022-01-28 02:45:00.000','2022-01-29 02:45:00.000',1111144444,3),
       ('2022-01-29 03:00:00.000','2022-01-30 03:00:00.000',1111155555,4),
       ('2022-01-30 03:15:00.000','2022-01-31 03:15:00.000',1111166666,2);
