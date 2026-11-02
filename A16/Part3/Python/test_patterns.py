import unittest

# --- 1. Repository Pattern ---
class Patient:
    def __init__(self, patient_id: str, name: str):
        self.id = patient_id
        self.name = name

class Repository:
    def __init__(self):
        self._db = {}
    
    def save(self, patient: Patient):
        self._db[patient.id] = patient
        
    def find_by_id(self, patient_id: str):
        return self._db.get(patient_id)
        
    def delete(self, patient_id: str):
        self._db.pop(patient_id, None)

# --- 2. Strategy Pattern ---
class StandardFeeStrategy:
    def calculate_fee(self, base_fee: float) -> float:
        return base_fee

class SeniorFeeStrategy:
    def calculate_fee(self, base_fee: float) -> float:
        return base_fee * 0.8

# --- 3. Observer Pattern ---
class AppointmentSubject:
    def __init__(self):
        self._observers = []
        
    def subscribe(self, observer):
        self._observers.append(observer)
        
    def notify(self, event: str):
        for obs in self._observers:
            obs(event)

class TestPatterns(unittest.TestCase):
    def test_repository(self):
        repo = Repository()
        p = Patient("P101", "John Doe")
        repo.save(p)
        self.assertEqual(repo.find_by_id("P101").name, "John Doe")
        repo.delete("P101")
        self.assertIsNone(repo.find_by_id("P101"))

    def test_strategy(self):
        standard = StandardFeeStrategy()
        senior = SeniorFeeStrategy()
        self.assertEqual(standard.calculate_fee(100.0), 100.0)
        self.assertEqual(senior.calculate_fee(100.0), 80.0)

    def test_observer(self):
        subject = AppointmentSubject()
        events = []
        subject.subscribe(lambda e: events.append(e))
        subject.notify("APPOINTMENT_CREATED")
        self.assertIn("APPOINTMENT_CREATED", events)

if __name__ == "__main__":
    unittest.main()
