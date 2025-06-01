CREATE TABLE material_type (
    id INT PRIMARY KEY,
    name text NOT NULL,
    percent int not null
);

INSERT INTO material_type (id, name, percent) VALUES
(1, 'Тип материала 1', 10),
(2, 'Тип материала 2', 95),
(3, 'Тип материала 3', 28),
(4, 'Тип материала 4', 55),
(5, 'Тип материала 5', 34);

CREATE TABLE production_type (
    id INT PRIMARY KEY,
    name text not null,
	coeff float not null
);

INSERT INTO production_type (id, name, coeff) VALUES
(1, 'Ламинат',         2.35),
(2, 'Массивная доска', 5.15),
(3, 'Паркетная доска', 4.34),
(4, 'Пробковое покрытие', 1.50);

CREATE TABLE partner (
    id INT PRIMARY KEY,
    partner_type text not null,
	name text not null,
	director text not null,
	director_email text not null,
	partner_phone text not null,
	partner_legal_address text not null,
	inn text not null,
	rating int not null
);

INSERT INTO partner (id, partner_type, name, director, director_email, partner_phone, partner_legal_address, inn, rating) VALUES
(1, 'ЗАО','База Строитель',  'Иванова Александра Ивановна',     'aleksandraivanova@ml.ru',   '493 123 45 67','652050, Кемеровская область, город Юрга, ул. Лесная, 15',      '2222455179',7),
(2, 'ООО','Паркет 29',       'Петров Василий Петрович',         'vppetrov@vl.ru',            '987 123 56 78','164500, Архангельская область, город Северодвинск, ул. Строителей, 18','3333888520',7),
(3, 'ПАО','Стройсервис',      'Соловьев Андрей Николаевич',      'ansolovev@st.ru',           '812 223 32 00','188910, Ленинградская область, город Приморск, ул. Парковая, 21',  '4440391035',7),
(4, 'ОАО','Ремонт и отделка', 'Воробьева Екатерина Валерьевна',  'ekaterina.vorobeva@ml.ru',  '444 222 33 11','143960, Московская область, город Реутов, ул. Свободы, 51',         '1111520857',5),
(5, 'ЗАО','МонтажПро',       'Степанов Степан Сергеевич',       'stepanov@stepan.ru',        '912 888 33 33','309500, Белгородская область, город Старый Оскол, ул. Рабочая, 122', '5552431140',10);

CREATE TABLE production (
    id INT PRIMARY KEY,
    type_id int not null,
	name text not null,
	articul text not null,
	min_price float not null,
    CONSTRAINT fk_production_type FOREIGN KEY (type_id) REFERENCES production_type(id)
);

INSERT INTO production (id, type_id, name, articul, min_price) VALUES
(1, 3, 'Паркетная доска Ясень темный однополосная 14 мм',       '8758385', 4456.90),
(2, 3, 'Инженерная доска Дуб Французская елка однополосная 12 мм', '8858958', 7330.99),
(3, 1, 'Ламинат Дуб дымчато-белый 33 класс 12 мм',              '7750282', 1799.33),
(4, 1, 'Ламинат Дуб серый 32 класс 8 мм с фаской',              '7028748', 3890.41),
(5, 4, 'Пробковое напольное клеевое покрытие 32 класс 4 мм',    '5012543', 5450.59);

CREATE TABLE partner_production (
    id INT PRIMARY KEY,
    production_id int not null,
	partner_id int not null,
	quantity bigint not null,
	sell_date date not null,
    CONSTRAINT fk_production FOREIGN KEY (production_id) REFERENCES production(id),
    CONSTRAINT fk_partner FOREIGN KEY (partner_id) REFERENCES partner(id)
);

INSERT INTO partner_production (id, production_id, partner_id, quantity, sell_date) VALUES
(1,1, 1, 15500,   '2023-03-23'),
(2,3, 1, 12350,   '2023-12-18'),
(3,4, 1, 37400,   '2024-06-07'),
(4,2, 2, 35000,   '2022-12-02'),
(5,5, 2, 1250,    '2023-05-17'),
(6,3, 2, 1000,    '2024-06-07'),
(7,1, 2, 7550,    '2024-07-01'),
(8,1, 3, 7250,    '2023-01-22'),
(9,2, 3, 2500,    '2024-07-05'),
(10,4,4, 59050,   '2023-03-20'),
(11,3,4, 37200,   '2024-03-12'),
(12,5,4, 4500,    '2024-05-14'),
(13,3,5, 50000,   '2023-09-19'),
(14,4,5, 670000,  '2023-11-10'),
(15,1,5, 35000,   '2024-04-15'),
(16,2,5, 25000,   '2024-06-12');