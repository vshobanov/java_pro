DROP TABLE IF EXISTS limits.limits;
CREATE TABLE limits.limits (
                               user_id int4 NULL,
                               dailyLimit int4 NULL
);

INSERT INTO limits (user_id,dailyLimit) VALUES
                                       (1,10000),
                                       (2,10000),
                                       (3,10000),
                                       (4,10000);