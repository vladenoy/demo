CREATE OR REPLACE FUNCTION insert_initial_balance()
RETURNS TRIGGER AS $$
BEGIN
  NEW.initial_balance = NEW.balance ;
  RETURN NEW ;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insert_initial_balance_trigger
BEFORE INSERT ON account
FOR EACH ROW
EXECUTE FUNCTION insert_initial_balance();