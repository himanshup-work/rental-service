BEGIN TRANSACTION;

DO $$
BEGIN

    CREATE TABLE IF NOT EXISTS rental_app_service.users (
        user_id VARCHAR(255) PRIMARY KEY NOT NULL,
        full_name VARCHAR(255) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL,
        password VARCHAR(255) NOT NULL
    );

END;
$$;
COMMIT TRANSACTION;