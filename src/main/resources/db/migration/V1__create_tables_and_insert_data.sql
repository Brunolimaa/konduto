CREATE TABLE credit_card_transactions (
    transaction_id VARCHAR(255) PRIMARY KEY,
    credit_card VARCHAR(255) NOT NULL
);

CREATE TABLE transaction_status (
    transaction_id VARCHAR(255) PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_transaction
        FOREIGN KEY (transaction_id)
        REFERENCES credit_card_transactions(transaction_id)
        ON DELETE CASCADE
);

INSERT INTO credit_card_transactions (transaction_id, credit_card) VALUES
('transacao1', '94548325069'),
('transacao2', '94548325069'),
('transacao3', '94548325069'),
('transacao4', '94548325069');

INSERT INTO transaction_status (transaction_id, status) VALUES
('transacao1', 'approved'),
('transacao2', 'approved'),
('transacao3', 'pending'),
('transacao4', 'declined');

INSERT INTO credit_card_transactions (transaction_id, credit_card) VALUES
('transacao_outro_cpf', '11122233344');
INSERT INTO transaction_status (transaction_id, status) VALUES
('transacao_outro_cpf', 'approved');