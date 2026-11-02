#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <memory>
#include <functional>
#include <cassert>

// --- 1. Repository Pattern ---
struct Patient {
    std::string id;
    std::string name;
};

class Repository {
private:
    std::unordered_map<std::string, Patient> db;
public:
    void save(const Patient& p) { db[p.id] = p; }
    Patient* findById(const std::string& id) {
        if (db.find(id) != db.end()) return &db[id];
        return nullptr;
    }
    void remove(const std::string& id) { db.erase(id); }
};

// --- 2. Strategy Pattern ---
class FeeStrategy {
public:
    virtual ~FeeStrategy() = default;
    virtual double calculateFee(double baseFee) const = 0;
};

class StandardFee : public FeeStrategy {
public:
    double calculateFee(double baseFee) const override { return baseFee; }
};

class SeniorFee : public FeeStrategy {
public:
    double calculateFee(double baseFee) const override { return baseFee * 0.8; }
};

// --- 3. Observer Pattern ---
class AppointmentSubject {
private:
    std::vector<std::function<void(const std::string&)>> observers;
public:
    void subscribe(std::function<void(const std::string&)> obs) {
        observers.push_back(obs);
    }
    void notify(const std::string& event) {
        for (auto& obs : observers) obs(event);
    }
};

int main() {
    std::cout << "=== Running C++ Pattern Benchmarks ===" << std::endl;

    // Test Repository
    Repository repo;
    repo.save({"P101", "John Doe"});
    assert(repo.findById("P101")->name == "John Doe");
    std::cout << "[C++] Repository Pattern: PASS" << std::endl;

    // Test Strategy
    StandardFee standard;
    SeniorFee senior;
    assert(standard.calculateFee(100.0) == 100.0);
    assert(senior.calculateFee(100.0) == 80.0);
    std::cout << "[C++] Strategy Pattern: PASS" << std::endl;

    // Test Observer
    AppointmentSubject subject;
    bool notified = false;
    subject.subscribe([&notified](const std::string& event) {
        if (event == "APPOINTMENT_CREATED") notified = true;
    });
    subject.notify("APPOINTMENT_CREATED");
    assert(notified == true);
    std::cout << "[C++] Observer Pattern: PASS" << std::endl;

    return 0;
}
