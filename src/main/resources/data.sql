DELETE FROM habitacions;
DELETE FROM hotels;
DELETE FROM categories;
INSERT INTO categories (id, nom) VALUES (1, 'Motel');
INSERT INTO categories (id, nom) VALUES (2, '1 estrella');
INSERT INTO categories (id, nom) VALUES (3, '2 estrelles');
INSERT INTO categories (id, nom) VALUES (4, '3 estrelles');
INSERT INTO categories (id, nom) VALUES (5, '4 estrelles');
INSERT INTO categories (id, nom) VALUES (6, '5 estrelles');



INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (1, 'Hotel de los Olivos', 'Carrer del Sol, 7', 'Tarragona', 1);
INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (2, 'Hotel Millor del món', 'Carrer de San Joan 1', 'Barcelona', 2);
INSERT INTO hotels (id, nom, adreca, ciutat, categoria_id) VALUES (3, 'Hotel San Viçens', 'Carrer de Bermudes 6', 'Valencia', 3);


INSERT INTO habitacions (numero, capacitat, descripcio, tipus, estat, preunitad, preunitmp, hotel_id)
 VALUES
(101, 2, 'Habitación doble con vistas al jardín', 'DOBLE', 'DISPONIBLE', 80.00, 60.00, 1),
(102, 2, 'Habitación doble con balcón', 'DOBLE', 'DISPONIBLE', 85.00, 65.00, 1),
(103, 3, 'Habitación triple con terraza', 'SUITE', 'OCUPADA', 100.00, 80.00, 1),
(104, 1, 'Habitación individual acogedora', 'SIMPLE', 'DISPONIBLE', 60.00, 45.00, 1),
(105, 2, 'Habitación doble con bañera de hidromasaje', 'DOBLE', 'OCUPADA', 120.00, 100.00, 1),
(106, 4, 'Suite familiar con sala de estar', 'SUITE', 'DISPONIBLE', 150.00, 130.00, 1),
(107, 2, 'Habitación doble estándar', 'DOBLE', 'RESERVADA', 75.00, 55.00, 1),
(108, 1, 'Habitación individual con escritorio', 'SIMPLE', 'DISPONIBLE', 65.00, 50.00, 1),
(109, 3, 'Habitación triple con vistas a la piscina', 'SUITE', 'DISPONIBLE', 110.00, 90.00, 1),
(110, 2, 'Habitación doble superior con balcón', 'DOBLE', 'DISPONIBLE', 95.00, 75.00, 1),



(201, 2, 'Habitación doble moderna con vista a la ciudad', 'DOBLE', 'DISPONIBLE', 150.00, 130.00, 2),
(202, 3, 'Habitación triple con terraza privada', 'SUITE', 'DISPONIBLE', 180.00, 160.00, 2),
(203, 1, 'Habitación individual con diseño minimalista', 'SIMPLE', 'OCUPADA', 100.00, 85.00, 2),
(204, 4, 'Suite ejecutiva con sala de estar', 'SUITE', 'DISPONIBLE', 250.00, 220.00, 2),
(205, 2, 'Habitación doble con jacuzzi', 'DOBLE', 'RESERVADA', 200.00, 180.00, 2),
(206, 3, 'Habitación triple con escritorio y minibar', 'SUITE', 'DISPONIBLE', 175.00, 155.00, 2),
(207, 2, 'Habitación doble clásica con detalles elegantes', 'DOBLE', 'DISPONIBLE', 160.00, 140.00, 2),
(208, 1, 'Habitación individual con vistas al parque', 'SIMPLE', 'OCUPADA', 105.00, 90.00, 2),
(209, 2, 'Habitación doble de lujo con iluminación ambiental', 'DOBLE', 'DISPONIBLE', 190.00, 170.00, 2),
(210, 4, 'Suite presidencial con terraza y jacuzzi', 'SUITE', 'RESERVADA', 300.00, 270.00, 2),


(301, 1, 'Habitación individual con decoración rústica', 'SIMPLE', 'DISPONIBLE', 55.00, 40.00, 3),
(302, 2, 'Habitación doble con cama king-size', 'DOBLE', 'DISPONIBLE', 90.00, 70.00, 3),
(303, 3, 'Habitación triple con balcón privado', 'SUITE', 'OCUPADA', 110.00, 85.00, 3),
(304, 4, 'Suite familiar con cocina y terraza', 'SUITE', 'DISPONIBLE', 170.00, 140.00, 3),
(305, 2, 'Habitación doble con baño de mármol', 'DOBLE', 'RESERVADA', 95.00, 75.00, 3),
(306, 3, 'Habitación triple con vistas al mar', 'SUITE', 'DISPONIBLE', 130.00, 110.00, 3),
(307, 1, 'Habitación individual con escritorio y wifi gratuito', 'SIMPLE', 'DISPONIBLE', 60.00, 50.00, 3),
(308, 2, 'Habitación doble con mobiliario clásico', 'DOBLE', 'OCUPADA', 85.00, 65.00, 3),
(309, 3, 'Habitación triple con decoración moderna', 'SUITE', 'DISPONIBLE', 120.00, 95.00, 3),
(310, 2, 'Habitación doble estándar con aire acondicionado', 'DOBLE', 'DISPONIBLE', 80.00, 60.00, 3);


