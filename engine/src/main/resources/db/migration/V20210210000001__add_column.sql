ALTER TABLE unit_moysklad_id
ADD COLUMN order_state_cancelled VARCHAR;
UPDATE unit_moysklad_id
SET order_state_cancelled = 'de0e5e4a-2d6b-11eb-0a80-02f30035f913';