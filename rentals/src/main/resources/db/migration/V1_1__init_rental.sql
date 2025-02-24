CREATE TABLE rental (
    id SERIAL PRIMARY KEY,
    book_id int,
    user_id int,
    rental_date TIMESTAMP WITH TIME ZONE,
    return_date TIMESTAMP WITH TIME ZONE,
    fee DECIMAL(10, 2)
);

INSERT INTO rental (book_id, user_id, rental_date, return_date, fee)
VALUES
    (1, 1, NOW(), NOW() + INTERVAL '10 days', 0.00),
    (2, 1, NOW(), NOW() + INTERVAL '7 days', 0.00),
    (3, 1, NOW(), null, 5.00);
