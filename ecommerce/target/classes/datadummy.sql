-- Users
INSERT INTO users (id, username, email, password, created_at) VALUES
(1, 'john_doe', 'john@example.com', 'password123', '2023-01-01 10:00:00'),
(2, 'jane_smith', 'jane@example.com', 'password123', '2023-01-02 11:00:00'),
(3, 'budi_utama', 'budi@example.com', 'password123', '2023-01-03 12:00:00');

-- Stores
INSERT INTO stores (id, name, description, location, active, rating, logo_url, owner_id, created_at) VALUES
(1, 'iStoreBdg', 'Official Apple Reseller', 'Bandung', true, 4.9, 'https://example.com/istorebdg.jpg', 1, '2023-01-05 09:00:00'),
(2, 'TechGadget', 'Teknologi Terkini', 'Jakarta', true, 4.7, 'https://example.com/techgadget.jpg', 2, '2023-01-06 10:00:00'),
(3, 'Elektronik Murah', 'Harga Terjangkau', 'Surabaya', true, 4.5, 'https://example.com/elektronikmurah.jpg', 3, '2023-01-07 11:00:00');

-- Categories (Hierarki Kategori)
INSERT INTO categories (id, name, parent_id, created_at) VALUES
-- Kategori utama
(1, 'Elektronik', NULL, '2023-01-10 08:00:00'),
(2, 'Fashion', NULL, '2023-01-10 08:00:00'),
-- Subkategori Elektronik
(3, 'Laptop', 1, '2023-01-10 09:00:00'),
(4, 'Smartphone', 1, '2023-01-10 09:00:00'),
(5, 'Tablet', 1, '2023-01-10 09:00:00'),
-- Subkategori Fashion
(6, 'Pria', 2, '2023-01-10 10:00:00'),
(7, 'Wanita', 2, '2023-01-10 10:00:00');

-- Products
INSERT INTO products (id, name, description, price, stock, weight, condition, store_id, category_id, created_at, updated_at) VALUES
-- Produk di iStoreBdg
(1, 'MacBook Pro 14" M2', 'Apple MacBook Pro 14 inch dengan chip M2', 36499000, 10, 1.6, 'Baru', 1, 3, '2023-01-15 10:00:00', '2023-01-15 10:00:00'),
(2, 'iPhone 15 Pro', 'Apple iPhone 15 Pro 128GB', 24999000, 15, 0.2, 'Baru', 1, 4, '2023-01-16 11:00:00', '2023-01-16 11:00:00'),
-- Produk di TechGadget
(3, 'Dell XPS 15', 'Laptop Dell XPS 15 dengan Intel i7', 22999000, 5, 1.8, 'Baru', 2, 3, '2023-01-17 12:00:00', '2023-01-17 12:00:00'),
(4, 'Samsung Galaxy S23', 'Smartphone Samsung Galaxy S23 256GB', 17999000, 8, 0.19, 'Baru', 2, 4, '2023-01-18 13:00:00', '2023-01-18 13:00:00'),
-- Produk di Elektronik Murah
(5, 'iPad Air', 'Apple iPad Air generasi terbaru', 12999000, 12, 0.46, 'Baru', 3, 5, '2023-01-19 14:00:00', '2023-01-19 14:00:00');

-- Product Variants
INSERT INTO product_variants (id, product_id, name, value, additional_price) VALUES
-- Variant MacBook Pro
(1, 1, 'Chip', 'M2 Pro', 0),
(2, 1, 'Chip', 'M2 Max', 5000000),
(3, 1, 'RAM', '16GB', 0),
(4, 1, 'RAM', '32GB', 3000000),
(5, 1, 'Storage', '512GB', 0),
(6, 1, 'Storage', '1TB', 4000000),
-- Variant iPhone
(7, 2, 'Warna', 'Silver', 0),
(8, 2, 'Warna', 'Space Grey', 0),
(9, 2, 'Storage', '128GB', 0),
(10, 2, 'Storage', '256GB', 2000000);

-- Product Images
INSERT INTO product_images (id, product_id, image_url, is_primary) VALUES
(1, 1, 'https://example.com/mbp14-1.jpg', true),
(2, 1, 'https://example.com/mbp14-2.jpg', false),
(3, 2, 'https://example.com/iphone15-1.jpg', true),
(4, 3, 'https://example.com/dellxps-1.jpg', true),
(5, 4, 'https://example.com/s23-1.jpg', true),
(6, 5, 'https://example.com/ipadair-1.jpg', true);

-- Cart Items
INSERT INTO carts (id, user_id, product_id, quantity, notes, created_at, updated_at) VALUES
(1, 1, 1, 1, 'Warna Space Grey', '2023-02-01 14:00:00', '2023-02-01 14:00:00'),
(2, 1, 2, 2, 'Storage 256GB', '2023-02-01 15:00:00', '2023-02-01 15:00:00'),
(3, 2, 3, 1, NULL, '2023-02-02 10:00:00', '2023-02-02 10:00:00');

-- Orders
INSERT INTO orders (id, invoice_number, user_id, store_id, status, total_amount, shipping_info, created_at, updated_at) VALUES
(1, 'INV-20230201-001', 1, 1, 'COMPLETED', 86497000, '{"courier": "JNE", "service": "REG", "address": "Jl. Merdeka No.1, Bandung"}', '2023-02-01 16:00:00', '2023-02-05 10:00:00'),
(2, 'INV-20230202-002', 2, 2, 'PROCESSING', 22999000, '{"courier": "SiCepat", "service": "EXPRESS", "address": "Jl. Sudirman No.45, Jakarta"}', '2023-02-02 11:00:00', '2023-02-02 11:00:00');

-- Order Items
INSERT INTO order_items (id, order_id, product_id, product_name, price, quantity, variant_info, created_at) VALUES
(1, 1, 1, 'MacBook Pro 14" M2', 36499000, 1, '{"chip": "M2 Pro", "ram": "16GB", "storage": "512GB"}', '2023-02-01 16:00:00'),
(2, 1, 2, 'iPhone 15 Pro', 24999000, 2, '{"warna": "Space Grey", "storage": "256GB"}', '2023-02-01 16:00:00'),
(3, 2, 3, 'Dell XPS 15', 22999000, 1, '{}', '2023-02-02 11:00:00');

-- Reset sequence untuk auto-increment
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('stores_id_seq', (SELECT MAX(id) FROM stores));
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));
SELECT setval('product_variants_id_seq', (SELECT MAX(id) FROM product_variants));
SELECT setval('product_images_id_seq', (SELECT MAX(id) FROM product_images));
SELECT setval('carts_id_seq', (SELECT MAX(id) FROM carts));
SELECT setval('orders_id_seq', (SELECT MAX(id) FROM orders));
SELECT setval('order_items_id_seq', (SELECT MAX(id) FROM order_items));