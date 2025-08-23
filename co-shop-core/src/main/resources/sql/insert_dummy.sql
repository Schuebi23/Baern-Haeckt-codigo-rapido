-- CO_SHOP Database Dummy Data Import - Grocery Store Focus
-- Set the search path to the CO_SHOP schema
SET search_path TO CO_SHOP;

-- Insert 4 Groups
INSERT INTO GROUPS (NAME, START_DATE, END_DATE, INVITE_CODE)
VALUES ('Family Smith', '2024-01-01', '2024-12-31', 'FAM1234'),
       ('Roommates Downtown', '2024-02-15', '2024-08-31', 'ROOM567'),
       ('Office Lunch Club', '2024-03-01', '2024-11-30', 'OFF8901'),
       ('Neighborhood Co-op', '2024-01-10', NULL, 'COOP234');

-- Insert 10 Members
INSERT INTO MEMBERS (DISPLAY_NAME)
VALUES ( 'Sarah Martinez'),
       ( 'Mike Johnson'),
       ( 'Lisa Chen'),
       ( 'David Rodriguez'),
       ( 'Emma Thompson'),
       ( 'James Wilson'),
       ( 'Anna Kowalski'),
       ( 'Tom Anderson'),
       ( 'Sophie Dubois'),
       ( 'Carlos Fernandez');

-- Insert Group Members (randomly distributed, members can be in multiple groups)
INSERT INTO GROUP_MEMBERS (GROUP_ID, MEMBER_ID)
VALUES
-- Family Smith
(1, 1),
(1, 4),
(1, 5),
(1, 7),
-- Roommates Downtown
(2, 2),
(2, 3),
(2, 6),
(2, 9),
-- Office Lunch Club
(3, 1),
(3, 3),
(3, 8),
(3, 10),
(3, 4),
-- Neighborhood Co-op
(4, 5),
(4, 6),
(4, 7),
(4, 8),
(4, 9),
(4, 10);

-- Insert 25 Standard Grocery Products
INSERT INTO PRODUCTS (NAME, UNIT)
VALUES ('Bread', 'loaf'),
       ('Milk', 'liter'),
       ('Eggs', 'dozen'),
       ('Chicken Breast', 'kg'),
       ('Ground Beef', 'kg'),
       ('Salmon Fillet', 'kg'),
       ('Bananas', 'kg'),
       ('Apples', 'kg'),
       ('Tomatoes', 'kg'),
       ('Onions', 'kg'),
       ('Potatoes', 'kg'),
       ('Carrots', 'kg'),
       ('Spinach', 'bunch'),
       ('Bell Peppers', 'kg'),
       ('Broccoli', 'head'),
       ('Rice', 'kg'),
       ('Pasta', 'pack'),
       ('Olive Oil', 'bottle'),
       ('Butter', 'pack'),
       ('Cheese', 'kg'),
       ('Yogurt', 'cup'),
       ('Orange Juice', 'liter'),
       ('Cereal', 'box'),
       ('Canned Tomatoes', 'can'),
       ('Black Beans', 'can');

INSERT INTO ITEMS (NAME, DESCRIPTION, UNIT, FK_GROUP, FK_PRODUCT)
VALUES
-- Family Smith items
('Weekly Bread', 'Whole wheat family bread', 'loaf', 1, 1),
('Family Milk', 'Organic whole milk', 'liter', 1, 2),
('Fresh Eggs', 'Free-range eggs', 'dozen', 1, 3),
('Dinner Chicken', 'Boneless chicken breast', 'kg', 1, 4),
('Weekly Bananas', 'Organic bananas', 'kg', 1, 7),
-- Roommates Downtown items
('Shared Bread', 'Daily bread for apartment', 'loaf', 2, 1),
('Apartment Milk', 'Shared refrigerator milk', 'liter', 2, 2),
('Cooking Onions', 'Yellow cooking onions', 'kg', 2, 10),
('Shared Rice', 'Jasmine rice for meals', 'kg', 2, 16),
('Cooking Oil', 'Extra virgin olive oil', 'bottle', 2, 18),
-- Office Lunch Club items
('Lunch Bread', 'Sandwich bread for office', 'loaf', 3, 1),
('Office Eggs', 'Eggs for lunch prep', 'dozen', 3, 3),
('Lunch Pasta', 'Quick lunch pasta', 'pack', 3, 17),
('Office Cheese', 'Sandwich cheese', 'kg', 3, 20),
('Lunch Tomatoes', 'Fresh sandwich tomatoes', 'kg', 3, 9),
-- Neighborhood Co-op items
('Co-op Milk', 'Bulk milk purchase', 'liter', 4, 2),
('Co-op Chicken', 'Bulk chicken order', 'kg', 4, 4),
('Co-op Apples', 'Seasonal apple variety', 'kg', 4, 8),
('Co-op Potatoes', 'Local farm potatoes', 'kg', 4, 11),
('Co-op Yogurt', 'Organic yogurt cups', 'cup', 4, 21);

-- Insert Requests
INSERT INTO REQUESTS (FK_ITEM, FK_MEMBER, QUANTITY, FOR_EVERYONE)
VALUES
-- Family Smith requests
(1, 1, 2, true),   -- Sarah requests bread for family
(1, 4, 2, true),   -- Sarah requests bread for family
(2, 4, 3, true),   -- David requests milk for family
(3, 5, 2, false),  -- Emma requests eggs
(4, 7, 2, true),   -- Anna requests chicken for family
(5, 1, 3, false),  -- Sarah requests bananas
-- Roommates Downtown requests
(6, 2, 1, true),   -- Mike requests shared bread
(7, 3, 2, true),   -- Lisa requests apartment milk
(8, 6, 2, false),  -- James requests onions
(9, 9, 1, true),   -- Sophie requests shared rice
(10, 2, 1, true),  -- Mike requests cooking oil
-- Office Lunch Club requests
(11, 1, 3, false), -- Sarah requests lunch bread
(12, 3, 1, true),  -- Lisa requests office eggs for everyone
(13, 8, 2, false), -- Tom requests lunch pasta
(14, 10, 1, true), -- Carlos requests office cheese for everyone
(15, 4, 2, false), -- David requests lunch tomatoes
-- Neighborhood Co-op requests
(16, 5, 10, true), -- Emma requests bulk milk for co-op
(17, 6, 5, true),  -- James requests bulk chicken for co-op
(18, 7, 8, false), -- Anna requests apples
(19, 8, 15, true), -- Tom requests bulk potatoes for co-op
(20, 9, 12, false); -- Sophie requests yogurt
