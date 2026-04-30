-- USERS
INSERT INTO users (name, email, password, enabled)
VALUES ('Алексей', 'alexey.ivanov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', true),
       ('Иван', 'ivan.petrov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', true),
       ('Мария', 'maria.sidorova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', true),
       ('Ольга', 'olga.kuznetsova@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', true),
       ('Дмитрий', 'dmitry.smirnov@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', true);

-- RESTAURANTS
INSERT INTO restaurant (name)
VALUES ('Узбечка №1'),
       ('Pizzeria Bella Italia'),
       ('Суши-Мастер'),
       ('Кавказский Дворик'),
       ('Бургерная "Крафт"'),
       ('Чайхана "Нават"'),
       ('Пельменная "Лепим и Варим"'),
       ('Стейк-хаус "Мясник"'),
       ('Кофейня "Зерно"'),
       ('Вьетнамский Уголок "Фо Бо"');

-- DISHES (по 3-5 на ресторан, без дубликатов)

-- 1
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Плов Чайханский', 'Узбекский плов с бараниной', 550, 1),
       ('Лагман', 'Суп с лапшой и мясом', 480, 1),
       ('Манты', 'Манты с говядиной', 450, 1),
       ('Самса', 'Слоеная выпечка с мясом', 120, 1),
       ('Шашлык', 'Шашлык из баранины', 650, 1);

-- 2
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Пицца Маргарита', 'Классическая пицца', 480, 2),
       ('Карбонара', 'Паста с беконом', 560, 2),
       ('Лазанья', 'Мясная лазанья', 620, 2),
       ('Ризотто', 'Ризотто с грибами', 590, 2);

-- 3
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Филадельфия', 'Ролл с лососем', 580, 3),
       ('Калифорния', 'Ролл с крабом', 550, 3),
       ('Суши тунец', 'Суши с тунцом', 220, 3),
       ('Мисо суп', 'Японский суп', 250, 3);

-- 4
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Хачапури', 'Сырная лепешка', 480, 4),
       ('Хинкали', 'Пельмени с бульоном', 450, 4),
       ('Харчо', 'Острый суп', 520, 4),
       ('Люля-кебаб', 'Кебаб из говядины', 590, 4);

-- 5
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Чизбургер', 'Классический бургер', 550, 5),
       ('BBQ бургер', 'Бургер с беконом', 620, 5),
       ('Картофель фри', 'Фри', 220, 5),
       ('Крылья', 'Куриные крылышки', 480, 5);

-- 6
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Бешбармак', 'Национальное блюдо', 850, 6),
       ('Куурдак', 'Жаркое', 720, 6),
       ('Боорсоки', 'Тесто жареное', 200, 6),
       ('Сорпо', 'Бульон', 380, 6);

-- 7
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Пельмени', 'Сибирские', 420, 7),
       ('Вареники', 'С картофелем', 350, 7),
       ('Борщ', 'Суп', 450, 7),
       ('Солянка', 'Мясной суп', 490, 7);

-- 8
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Рибай', 'Стейк', 2500, 8),
       ('Филе-миньон', 'Нежный стейк', 2800, 8),
       ('Нью-Йорк', 'Стейк', 2200, 8),
       ('Овощи гриль', 'Гарнир', 450, 8);

-- 9
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Капучино', 'Кофе', 250, 9),
       ('Чизкейк', 'Десерт', 380, 9),
       ('Круассан', 'Выпечка', 220, 9),
       ('Латте', 'Кофе', 280, 9);

-- 10
INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Фо Бо', 'Суп', 580, 10),
       ('Нэмы', 'Спринг роллы', 420, 10),
       ('Бун Ча', 'Свинина с лапшой', 650, 10),
       ('Том Ям', 'Острый суп', 620, 10);