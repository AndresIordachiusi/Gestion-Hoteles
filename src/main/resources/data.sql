DELETE FROM empleats_tasques;
DELETE FROM tasques;

DELETE FROM reserves;
DELETE FROM clients;
DELETE FROM empleats;
DELETE FROM habitacions;
DELETE FROM hotels;
DELETE FROM categories;
DELETE FROM usuaris;
INSERT INTO categories (id, nom) VALUES (1, 'Motel');
INSERT INTO categories (id, nom) VALUES (2, '1 estrella');
INSERT INTO categories (id, nom) VALUES (3, '2 estrelles');
INSERT INTO categories (id, nom) VALUES (4, '3 estrelles');
INSERT INTO categories (id, nom) VALUES (5, '4 estrelles');
INSERT INTO categories (id, nom) VALUES (6, '5 estrelles');



INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (1, 'Hotel de los Olivos', 'Carrer del Sol, 7', 'Tarragona', 6);
INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (2, 'Hotel Silver Fusion', 'Carrer de San Joan, 1', 'Barcelona', 5);
INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (3, 'Hotel San Viçens', 'Carrer de Bermudes, 6', 'Valencia', 6);


INSERT INTO habitacions (id, numero, capacitat, descripcio, tipus, estat, preunitad, preunitmp, hotel_id)
 VALUES
(1, 101, 2, 'Habitación doble con vistas al jardín', 'DOBLE', 'DISPONIBLE', 80.00, 60.00, 1),
(2, 102, 2, 'Habitación doble con balcón', 'DOBLE', 'DISPONIBLE', 85.00, 65.00, 1),
(3, 103, 3, 'Habitación triple con terraza', 'SUITE', 'DISPONIBLE', 100.00, 80.00, 1),
(4, 104, 1, 'Habitación individual acogedora', 'SIMPLE', 'DISPONIBLE', 60.00, 45.00, 1),
(5, 105, 2, 'Habitación doble con bañera de hidromasaje', 'DOBLE', 'DISPONIBLE', 120.00, 100.00, 1),
(6, 106, 4, 'Suite familiar con sala de estar', 'SUITE', 'DISPONIBLE', 150.00, 130.00, 1),
(7, 107, 2, 'Habitación doble estándar', 'DOBLE', 'DISPONIBLE', 75.00, 55.00, 1),
(8, 108, 1, 'Habitación individual con escritorio', 'SIMPLE', 'DISPONIBLE', 65.00, 50.00, 1),
(9, 109, 3, 'Habitación triple con vistas a la piscina', 'SUITE', 'DISPONIBLE', 110.00, 90.00, 1),
(10, 110, 2, 'Habitación doble superior con balcón', 'DOBLE', 'DISPONIBLE', 95.00, 75.00, 1),



(11, 201, 2, 'Habitación doble moderna con vista a la ciudad', 'DOBLE', 'DISPONIBLE', 150.00, 130.00, 2),
(12, 202, 3, 'Habitación triple con terraza privada', 'SUITE', 'DISPONIBLE', 180.00, 160.00, 2),
(13, 203, 1, 'Habitación individual con diseño minimalista', 'SIMPLE', 'DISPONIBLE', 100.00, 85.00, 2),
(14, 204, 4, 'Suite ejecutiva con sala de estar', 'SUITE', 'DISPONIBLE', 250.00, 220.00, 2),
(15, 205, 2, 'Habitación doble con jacuzzi', 'DOBLE', 'DISPONIBLE', 200.00, 180.00, 2),
(16, 206, 3, 'Habitación triple con escritorio y minibar', 'SUITE', 'DISPONIBLE', 175.00, 155.00, 2),
(17, 207, 2, 'Habitación doble clásica con detalles elegantes', 'DOBLE', 'DISPONIBLE', 160.00, 140.00, 2),
(18, 208, 1, 'Habitación individual con vistas al parque', 'SIMPLE', 'DISPONIBLE', 105.00, 90.00, 2),
(19, 209, 2, 'Habitación doble de lujo con iluminación ambiental', 'DOBLE', 'DISPONIBLE', 190.00, 170.00, 2),
(20, 210, 4, 'Suite presidencial con terraza y jacuzzi', 'SUITE', 'DISPONIBLE', 300.00, 270.00, 2),


(21, 301, 1, 'Habitación individual con decoración rústica', 'SIMPLE', 'DISPONIBLE', 55.00, 40.00, 3),
(22, 302, 2, 'Habitación doble con cama king-size', 'DOBLE', 'DISPONIBLE', 90.00, 70.00, 3),
(23, 303, 3, 'Habitación triple con balcón privado', 'SUITE', 'DISPONIBLE', 110.00, 85.00, 3),
(24, 304, 4, 'Suite familiar con cocina y terraza', 'SUITE', 'DISPONIBLE', 170.00, 140.00, 3),
(25, 305, 2, 'Habitación doble con baño de mármol', 'DOBLE', 'DISPONIBLE', 95.00, 75.00, 3),
(26, 306, 3, 'Habitación triple con vistas al mar', 'SUITE', 'DISPONIBLE', 130.00, 110.00, 3),
(27, 307, 1, 'Habitación individual con escritorio y wifi gratuito', 'SIMPLE', 'DISPONIBLE', 60.00, 50.00, 3),
(28, 308, 2, 'Habitación doble con mobiliario clásico', 'DOBLE', 'DISPONIBLE', 85.00, 65.00, 3),
(29, 309, 3, 'Habitación triple con decoración moderna', 'SUITE', 'DISPONIBLE', 120.00, 95.00, 3),
(30, 310, 2, 'Habitación doble estándar con aire acondicionado', 'DOBLE', 'DISPONIBLE', 80.00, 60.00, 3);


INSERT INTO clients (id, adreça, cognom, data_naixement, document_identitat, email, nom, telefon, data_registre, targeta_credit, tipus)
VALUES
(500, 'Carrer Major 123, Barcelona', 'Garcia', '1980-05-15', '12345678A', 'garcia@gmail.com', 'Joan', '612345678', '2023-10-26', '675478984', 'REGULAR'),
(501, 'Avinguda Diagonal 45, Girona', 'Martinez', '1975-12-01', '98765432B', 'martinez@gmail.com', 'Maria', '698765432', '2023-10-26', '975481675', 'VIP'),
(502, 'Plaça Catalunya 10, Tarragona', 'Lopez', '1992-08-20', '56789012C', 'pere@gmail.com', 'Pere', '656789012', '2023-10-26', '637814975', 'PREMIUM'),
(503, 'Rambla Nova 22, Lleida', 'Sanchez', '1988-03-10', '01234567D', 'anna@gmail.com', 'Anna', '601234567', '2023-10-26', '32487946', 'REGULAR'),
(504, 'Gran Via 30, Manresa', 'Perez', '1970-06-25', '43210987E', 'josep@gmail.com', 'Josep', '643210987', '2023-10-26', '424784915', 'VIP');


INSERT INTO usuaris (id, actiu, email, password, rol, username) VALUES (20, true, "andres@client.com", "secret", "ROLE_CLIENT", "andres");
INSERT INTO clients (id, adreça, cognom, data_naixement, document_identitat, email, nom, telefon, usuari_id, data_registre, targeta_credit, tipus)
VALUES
(505, 'Calle del Sol, 45', 'Iordachiusi', '2006-11-30', 'Y1887711', 'andres@client.com', 'Andres', '677889977', 20, '2020-11-27', '21212121', 'VIP');


INSERT INTO empleats (id, adreça, cognom, data_naixement, document_identitat, email, nom, telefon, data_contractacio, estat, lloc_feina, salari_brut)
VALUES
(500, 'Carrer Nou 1, Barcelona', 'Vazquez', '1990-01-15', '12345678A', 'vazquez@gmail.com', 'Lucas', '612345678', '2020-03-01', 'ACTIU', 'Recepcionista', 30000),
(501, 'Avinguda Gran 2, Girona', 'Camavinga', '1985-06-22', '98765432B', 'camavinga@gmail.com', 'Eduardo', '698765432', '2019-09-15', 'ACTIU', 'Cambrer', 28000),
(502, 'Plaça Central 3, Tarragona', 'Mendy', '1995-11-10', '56789012C', 'mendy@gmail.com', 'Ferland', '656789012', '2021-01-05', 'PERMIS', 'Conserge', 32000),
(503, 'Rambla Vella 4, Lleida', 'Diaz', '1982-04-03', '01234567D', 'diaz@gmail.com', 'Brahim', '601234567', '2018-11-20', 'ACTIU', 'Gerent', 45000),
(504, 'Gran Via 5, Manresa', 'Courtois', '1978-09-18', '43210987E', 'courtois@gmail.com', 'Thibaut', '643210987', '2017-07-10', 'BAIXA', 'Comptable', 35000),
(505, 'Carrer Estret 6, Vic', 'Rodriguez', '1998-02-28', '87654321F', 'rodriguez@gmail.com', 'Laura', '687654321', '2022-05-01', 'ACTIU', 'Manteniment', 29000),
(506, 'Avinguda Ampla 7, Igualada', 'Gonzalez', '1987-07-05', '21098765G', 'gonzalez@gmail.com', 'David', '621098765', '2020-12-15', 'ACTIU', 'Cuiner', 27000),
(507, 'Plaça Petita 8, Olot', 'Fernandez', '1993-12-08', '65432109H', 'fernandez@gmail.com', 'Silvia', '665432109', '2021-08-01', 'PERMIS', 'Administratiu', 25000),
(508, 'Rambla Ampla 9, Reus', 'Ruiz', '1980-05-12', '98765432I', 'ruiz@gmail.com', 'Carlos', '698765432', '2019-05-20', 'ACTIU', 'Venedor', 26000),
(509, 'Gran Avinguda 10, Mataró', 'Jimenez', '1975-10-25', '32109876J', 'jimenez@gmail.com', 'Isabel', '632109876', '2018-02-10', 'BAIXA', 'Jardiner', 38000);







INSERT INTO tasques (id, data_creacio, data_ejecucio, descripcio, estat)
VALUES
(1, '2025-03-12', '2025-03-13', 'Neteja lavabos', 'PENDENT'),
(2, '2025-03-18', '2025-03-19', 'Revisión del sistema de climatización', 'COMPLETADA'),
(3, '2025-03-21', '2025-04-23', 'Reparación de cerraduras en habitaciones', 'EN_PROGRÉS'),
(4, '2025-05-19', '2025-06-21', 'Inventario de suministros de limpieza', 'COMPLETADA'),
(5, '2025-06-22', '2025-07-24', 'Mantenimiento del ascensor principal', 'PENDENT');

INSERT INTO empleats_tasques (empleats_id, tasques_id)
VALUES
(500, 1),
(501, 1),
(502, 2),
(503, 5),
(504, 5),
(505, 3),
(506, 4);

INSERT INTO usuaris (id, actiu, email, password, rol, username) VALUES (50, true, "admin@admin.com", "secret", "ROLE_ADMIN", "admin");
