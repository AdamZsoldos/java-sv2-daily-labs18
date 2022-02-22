create table books (
    id bigint auto_increment,
    title varchar(255),
    author varchar(255),
    price bigint,
    number_in_stock bigint,
    constraint pk_books primary key (id)
);
