INSERT INTO  person(person_type, svnr, dateofbirth,
                    driverlicencenumber, firstname, lastname,
                    phonenumber)
VALUES ('Mech', '1234567890', '2002-02-02',
        'AT-123-445', 'Max', 'Mechaniker',
        '028457472812'),
       ('Mech', '9999999999', '2002-03-03',
        'AT-144-495', 'Lina', 'Lila',
        '95786893092');

INSERT INTO mechanic(priceperhour, workend, workstart, person_id)
VALUES (20, '12:00', '08:00', '1234567890'),
       (45.4, '17:00', '12:00', '9999999999');