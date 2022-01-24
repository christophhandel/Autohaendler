INSERT INTO  person(person_type, svnr, dateofbirth,
                    driverlicencenumber, firstname, lastname,
                    phonenumber)
VALUES ('Mech', '1234567890', '2002-02-02','AT-123-445', 'Max', 'Mechaniker','028457472812'),
       ('Mech', '9999999999', '2002-03-03','AT-144-495', 'Lina', 'Lila','95786893092'),
       ('Mech','1874206999', '1969','AT-187-420','Werner', 'Kaiser','06601234567'),
       ('Mech','4206999187', '1999','AT-420-187','Philipp', 'Kerschbaum','06607654321'),
       ('Mech','6999187420', '1978','AT-333-666','Kristof', 'Handl','06604206999');

INSERT INTO mechanic(priceperhour, workend, workstart, person_id)
VALUES (20, '12:00', '08:00', '1234567890'),
       (45.4, '17:00', '12:00', '9999999999'),
       (50.1, '04:00', '20:00', '8888888888'),
       (51.2, '05:00', '21:00', '7777777777'),
       (52.2, '06:00', '22:00', '6666666666');

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
VALUES ('Continental M&S 275-17R-55', 'Reifen', 10),
       ('Continental M&S 275-17R-55', 'Reifen', 5),
       ('Continental M&S 275-17R-55', 'Reifen', 7),
       ('Continental M&S 275-17R-55', 'Reifen', 3),
       ('Continental M&S 275-17R-55', 'Reifen', 20);
