CREATE TABLE IF NOT EXISTS pagamento (
                                         id INTEGER PRIMARY KEY,
                                         pedido_id INTEGER NOT NULL,
                                         data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                         qr_code TEXT,
                                         status_pedido_id INTEGER NOT NULL
);

