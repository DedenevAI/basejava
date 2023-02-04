create table public.resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text     NOT NULL
);

alter table public.resume
    owner to postgres;


create table public.contact
(
    id          serial
        constraint contact_pk
            primary key,
    type        text not null,
    value       text not null,
    resume_uuid char(36)
        constraint contact_pk2
            unique
        constraint contact_resume_uuid_fk
            references public.resume
            on delete cascade
);

create table public.section
(
    id          SERIAL PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    content     TEXT     NOT NULL
);

alter table public.contact
    owner to postgres;

create unique index contact__uuid_type_index
    on contact (resume_uuid, type);

create unique index section_idx ON section (resume_uuid, type);

