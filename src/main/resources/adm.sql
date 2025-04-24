INSERT INTO usuarios (id, nome_completo, email, senha, role) VALUES
(gen_random_uuid(),'admin','admin@ecoalerta.com','$2a$12$LbTC7gdy7Ts5Ff/hQT8RX.UVPRB5n.q8BiAle1SZjrZD3qVX9.uPK','ADMIN')
ON CONFLICT (email) DO NOTHING;