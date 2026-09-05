create table if not exists image
(
    id          varchar
        constraint image_pk primary key,
    filename    varchar,
    bucket_key  varchar,
    mime_type   varchar,
    size        bigint,
    created_at  timestamp with time zone
);

create table if not exists email_download_request
(
    id              varchar
        constraint email_download_request_pk primary key,
    recipient_email varchar,
    subject         varchar,
    body            varchar,
    download_token  varchar,
    created_at      timestamp with time zone
);

create table if not exists email_download_request_image
(
    id         varchar
        constraint email_download_request_image_pk primary key,
    request_id varchar,
    image_id   varchar
);