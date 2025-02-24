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

