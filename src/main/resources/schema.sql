use test;
--기존 인덱스 삭제
--ALTER TABLE review DROP INDEX review_idx;
--ALTER TABLE user_point DROP INDEX user_point_idx;

--테이블 삭제
drop table review;
drop table image;
drop table user_point;

CREATE TABLE review (
    review_id VARCHAR(36) NOT NULL,
    place_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36),
    content VARCHAR(1000),
    create_date DATETIME
);
CREATE INDEX review_idx ON review(review_id, place_id, user_id);

CREATE TABLE image (
    review_id VARCHAR(36) NOT NULL,
    attached_photo_id VARCHAR(36)
);

CREATE TABLE user_point (
    user_id VARCHAR(36) NOT NULL,
    review_id VARCHAR(36) NOT NULL,
    point_type VARCHAR(500),
    point INT,
    create_date DATETIME
);
CREATE INDEX user_point_idx ON user_point(user_id, point);