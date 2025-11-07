BEGIN TRANSACTION;

DO $$
BEGIN

    INSERT INTO rental_app_service.roles (id, name)
    VALUE (1, 'ADMIN');
    INSERT INTO rental_app_service.roles (id, name)
    VALUE (2, 'OWNER');
    INSERT INTO rental_app_service.roles (id, name)
    VALUE (3, 'TENANT');

END;
$$;
COMMIT TRANSACTION;