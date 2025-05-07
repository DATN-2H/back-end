-- Insert initial data into the database
INSERT INTO role (name, description, created_at, updated_at, status, id)
VALUES
  ('MANAGER',       'Restaurant manager',           NOW(), NOW(), 'ACTIVE', 1),
  ('WAITER',        'Table service staff',          NOW(), NOW(), 'ACTIVE', 2),
  ('HOST',          'Guest reception host',         NOW(), NOW(), 'ACTIVE', 3),
  ('KITCHEN',       'Kitchen staff',                NOW(), NOW(), 'ACTIVE', 4),
  ('CASHIER',       'Cashier',                      NOW(), NOW(), 'ACTIVE', 5),
  ('ACCOUNTANT',    'Accountant',                   NOW(), NOW(), 'ACTIVE', 6),
  ('EMPLOYEE',      'General employee',             NOW(), NOW(), 'ACTIVE', 7),
  ('CUSTOMER',      'Customer',                     NOW(), NOW(), 'ACTIVE', 8),
  ('SUPPORT',       'Technical support staff',      NOW(), NOW(), 'ACTIVE', 9),
  ('SYSTEM_ADMIN',  'System administrator',         NOW(), NOW(), 'ACTIVE', 10);

INSERT INTO permission (id, code, name, permission_group, method, pattern, status, created_at, updated_at) VALUES
-- MANAGER (role_id = 1)
(1,  'GET_MANAGER_API',    'Get Manager API',    'MANAGER',    'GET',    '/api/manager',        'ACTIVE', NOW(), NOW()),
(2,  'POST_MANAGER_API',   'Post Manager API',   'MANAGER',    'POST',   '/api/manager',        'ACTIVE', NOW(), NOW()),
(3,  'PUT_MANAGER_API',    'Put Manager API',    'MANAGER',    'PUT',    '/api/manager/{id}',   'ACTIVE', NOW(), NOW()),
(4,  'DELETE_MANAGER_API', 'Delete Manager API', 'MANAGER',    'DELETE', '/api/manager/{id}',   'ACTIVE', NOW(), NOW()),

-- WAITER (role_id = 2)
(5,  'GET_WAITER_API',     'Get Waiter API',     'WAITER',     'GET',    '/api/waiter',         'ACTIVE', NOW(), NOW()),
(6,  'POST_WAITER_API',    'Post Waiter API',    'WAITER',     'POST',   '/api/waiter',         'ACTIVE', NOW(), NOW()),
(7,  'PUT_WAITER_API',     'Put Waiter API',     'WAITER',     'PUT',    '/api/waiter/{id}',    'ACTIVE', NOW(), NOW()),
(8,  'DELETE_WAITER_API',  'Delete Waiter API',  'WAITER',     'DELETE', '/api/waiter/{id}',    'ACTIVE', NOW(), NOW()),

-- HOST (role_id = 3)
(9,  'GET_HOST_API',       'Get Host API',       'HOST',       'GET',    '/api/host',           'ACTIVE', NOW(), NOW()),
(10, 'POST_HOST_API',      'Post Host API',      'HOST',       'POST',   '/api/host',           'ACTIVE', NOW(), NOW()),
(11, 'PUT_HOST_API',       'Put Host API',       'HOST',       'PUT',    '/api/host/{id}',      'ACTIVE', NOW(), NOW()),
(12, 'DELETE_HOST_API',    'Delete Host API',    'HOST',       'DELETE', '/api/host/{id}',      'ACTIVE', NOW(), NOW()),

-- KITCHEN (role_id = 4)
(13, 'GET_KITCHEN_API',    'Get Kitchen API',    'KITCHEN',    'GET',    '/api/kitchen',        'ACTIVE', NOW(), NOW()),
(14, 'POST_KITCHEN_API',   'Post Kitchen API',   'KITCHEN',    'POST',   '/api/kitchen',        'ACTIVE', NOW(), NOW()),
(15, 'PUT_KITCHEN_API',    'Put Kitchen API',    'KITCHEN',    'PUT',    '/api/kitchen/{id}',   'ACTIVE', NOW(), NOW()),
(16, 'DELETE_KITCHEN_API', 'Delete Kitchen API', 'KITCHEN',    'DELETE', '/api/kitchen/{id}',   'ACTIVE', NOW(), NOW()),

-- CASHIER (role_id = 5)
(17, 'GET_CASHIER_API',    'Get Cashier API',    'CASHIER',    'GET',    '/api/cashier',        'ACTIVE', NOW(), NOW()),
(18, 'POST_CASHIER_API',   'Post Cashier API',   'CASHIER',    'POST',   '/api/cashier',        'ACTIVE', NOW(), NOW()),
(19, 'PUT_CASHIER_API',    'Put Cashier API',    'CASHIER',    'PUT',    '/api/cashier/{id}',   'ACTIVE', NOW(), NOW()),
(20, 'DELETE_CASHIER_API', 'Delete Cashier API', 'CASHIER',    'DELETE', '/api/cashier/{id}',   'ACTIVE', NOW(), NOW()),

-- ACCOUNTANT (role_id = 6)
(21, 'GET_ACCOUNTANT_API',    'Get Accountant API',    'ACCOUNTANT',    'GET',    '/api/accountant',        'ACTIVE', NOW(), NOW()),
(22, 'POST_ACCOUNTANT_API',   'Post Accountant API',   'ACCOUNTANT',    'POST',   '/api/accountant',        'ACTIVE', NOW(), NOW()),
(23, 'PUT_ACCOUNTANT_API',    'Put Accountant API',    'ACCOUNTANT',    'PUT',    '/api/accountant/{id}',   'ACTIVE', NOW(), NOW()),
(24, 'DELETE_ACCOUNTANT_API', 'Delete Accountant API', 'ACCOUNTANT',    'DELETE', '/api/accountant/{id}',   'ACTIVE', NOW(), NOW()),

-- EMPLOYEE (role_id = 7)
(25, 'GET_EMPLOYEE_API',    'Get Employee API',    'EMPLOYEE',    'GET',    '/api/employee',        'ACTIVE', NOW(), NOW()),
(26, 'POST_EMPLOYEE_API',   'Post Employee API',   'EMPLOYEE',    'POST',   '/api/employee',        'ACTIVE', NOW(), NOW()),
(27, 'PUT_EMPLOYEE_API',    'Put Employee API',    'EMPLOYEE',    'PUT',    '/api/employee/{id}',   'ACTIVE', NOW(), NOW()),
(28, 'DELETE_EMPLOYEE_API', 'Delete Employee API', 'EMPLOYEE',    'DELETE', '/api/employee/{id}',   'ACTIVE', NOW(), NOW()),

-- CUSTOMER (role_id = 8)
(29, 'GET_CUSTOMER_API',    'Get Customer API',    'CUSTOMER',    'GET',    '/api/customer',        'ACTIVE', NOW(), NOW()),
(30, 'POST_CUSTOMER_API',   'Post Customer API',   'CUSTOMER',    'POST',   '/api/customer',        'ACTIVE', NOW(), NOW()),
(31, 'PUT_CUSTOMER_API',    'Put Customer API',    'CUSTOMER',    'PUT',    '/api/customer/{id}',   'ACTIVE', NOW(), NOW()),
(32, 'DELETE_CUSTOMER_API', 'Delete Customer API', 'CUSTOMER',    'DELETE', '/api/customer/{id}',   'ACTIVE', NOW(), NOW()),

-- SUPPORT (role_id = 9)
(33, 'GET_SUPPORT_API',    'Get Support API',    'SUPPORT',    'GET',    '/api/support',        'ACTIVE', NOW(), NOW()),
(34, 'POST_SUPPORT_API',   'Post Support API',   'SUPPORT',    'POST',   '/api/support',        'ACTIVE', NOW(), NOW()),
(35, 'PUT_SUPPORT_API',    'Put Support API',    'SUPPORT',    'PUT',    '/api/support/{id}',   'ACTIVE', NOW(), NOW()),
(36, 'DELETE_SUPPORT_API', 'Delete Support API', 'SUPPORT',    'DELETE', '/api/support/{id}',   'ACTIVE', NOW(), NOW()),

-- SYSTEM_ADMIN (role_id = 10)
(37, 'GET_SYSTEM_ADMIN_API',    'Get System Admin API',    'SYSTEM_ADMIN',    'GET',    '/api/system-admin',        'ACTIVE', NOW(), NOW()),
(38, 'POST_SYSTEM_ADMIN_API',   'Post System Admin API',   'SYSTEM_ADMIN',    'POST',   '/api/system-admin',        'ACTIVE', NOW(), NOW()),
(39, 'PUT_SYSTEM_ADMIN_API',    'Put System Admin API',    'SYSTEM_ADMIN',    'PUT',    '/api/system-admin/{id}',   'ACTIVE', NOW(), NOW()),
(40, 'DELETE_SYSTEM_ADMIN_API', 'Delete System Admin API', 'SYSTEM_ADMIN',    'DELETE', '/api/system-admin/{id}',   'ACTIVE', NOW(), NOW());

INSERT INTO role_permission (id, role_id, permission_id, status, created_at, updated_at) VALUES
-- MANAGER (1)
(1, 1, 1,  'ACTIVE', NOW(), NOW()),
(2, 1, 2,  'ACTIVE', NOW(), NOW()),
(3, 1, 3,  'ACTIVE', NOW(), NOW()),
(4, 1, 4,  'ACTIVE', NOW(), NOW()),

-- WAITER (2)
(5, 2, 5,  'ACTIVE', NOW(), NOW()),
(6, 2, 6,  'ACTIVE', NOW(), NOW()),
(7, 2, 7,  'ACTIVE', NOW(), NOW()),
(8, 2, 8,  'ACTIVE', NOW(), NOW()),

-- HOST (3)
(9, 3, 9,   'ACTIVE', NOW(), NOW()),
(10, 3, 10, 'ACTIVE', NOW(), NOW()),
(11, 3, 11, 'ACTIVE', NOW(), NOW()),
(12, 3, 12, 'ACTIVE', NOW(), NOW()),

-- KITCHEN (4)
(13, 4, 13, 'ACTIVE', NOW(), NOW()),
(14, 4, 14, 'ACTIVE', NOW(), NOW()),
(15, 4, 15, 'ACTIVE', NOW(), NOW()),
(16, 4, 16, 'ACTIVE', NOW(), NOW()),

-- CASHIER (5)
(17, 5, 17, 'ACTIVE', NOW(), NOW()),
(18, 5, 18, 'ACTIVE', NOW(), NOW()),
(19, 5, 19, 'ACTIVE', NOW(), NOW()),
(20, 5, 20, 'ACTIVE', NOW(), NOW()),

-- ACCOUNTANT (6)
(21, 6, 21, 'ACTIVE', NOW(), NOW()),
(22, 6, 22, 'ACTIVE', NOW(), NOW()),
(23, 6, 23, 'ACTIVE', NOW(), NOW()),
(24, 6, 24, 'ACTIVE', NOW(), NOW()),

-- EMPLOYEE (7)
(25, 7, 25, 'ACTIVE', NOW(), NOW()),
(26, 7, 26, 'ACTIVE', NOW(), NOW()),
(27, 7, 27, 'ACTIVE', NOW(), NOW()),
(28, 7, 28, 'ACTIVE', NOW(), NOW()),

-- CUSTOMER (8)
(29, 8, 29, 'ACTIVE', NOW(), NOW()),
(30, 8, 30, 'ACTIVE', NOW(), NOW()),
(31, 8, 31, 'ACTIVE', NOW(), NOW()),
(32, 8, 32, 'ACTIVE', NOW(), NOW()),

-- SUPPORT (9)
(33, 9, 33, 'ACTIVE', NOW(), NOW()),
(34, 9, 34, 'ACTIVE', NOW(), NOW()),
(35, 9, 35, 'ACTIVE', NOW(), NOW()),
(36, 9, 36, 'ACTIVE', NOW(), NOW()),

-- SYSTEM_ADMIN (10)
(37, 10, 37, 'ACTIVE', NOW(), NOW()),
(38, 10, 38, 'ACTIVE', NOW(), NOW()),
(39, 10, 39, 'ACTIVE', NOW(), NOW()),
(40, 10, 40, 'ACTIVE', NOW(), NOW());
