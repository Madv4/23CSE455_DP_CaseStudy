const assert = require('assert');

// --- 1. Repository Pattern ---
class Patient {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}

class Repository {
    constructor() {
        this.db = new Map();
    }
    save(patient) {
        this.db.set(patient.id, patient);
    }
    findById(id) {
        return this.db.get(id);
    }
    delete(id) {
        this.db.delete(id);
    }
}

// --- 2. Strategy Pattern ---
class StandardFeeStrategy {
    calculateFee(baseFee) { return baseFee; }
}

class SeniorFeeStrategy {
    calculateFee(baseFee) { return baseFee * 0.8; }
}

// --- 3. Observer Pattern ---
class AppointmentSubject {
    constructor() {
        this.observers = [];
    }
    subscribe(observer) {
        this.observers.push(observer);
    }
    notify(event) {
        this.observers.forEach(obs => obs(event));
    }
}

// Run Tests
console.log("=== Running JavaScript Pattern Benchmarks ===");

// Repository Test
const repo = new Repository();
repo.save(new Patient("P101", "John Doe"));
assert.strictEqual(repo.findById("P101").name, "John Doe");
console.log("[JS] Repository Pattern: PASS");

// Strategy Test
const standard = new StandardFeeStrategy();
const senior = new SeniorFeeStrategy();
assert.strictEqual(standard.calculateFee(100), 100);
assert.strictEqual(senior.calculateFee(100), 80);
console.log("[JS] Strategy Pattern: PASS");

// Observer Test
const subject = new AppointmentSubject();
let received = false;
subject.subscribe(e => { if (e === 'APPOINTMENT_CREATED') received = true; });
subject.notify('APPOINTMENT_CREATED');
assert.strictEqual(received, true);
console.log("[JS] Observer Pattern: PASS");
