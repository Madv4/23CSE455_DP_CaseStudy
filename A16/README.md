# Team A16 — Hospital Management System Design Pattern Case Study

## Team
| Registration number | Name |
|---|---|
| AM.EN.U4EAC23033 | Sai Krishna |
| AM.EN.U4EAC23042 | Siddhartha |
| AM.EN.U4EAC23048 | Sai Rudra |
| AM.EN.U4EAC23064 | Shradhey K |
| AM.EN.U4EAC23025 | Pavan Sai |

## Package contents
- `A16_Report.pdf`: compiled report.
- `A16_Report.tex`: editable LaTeX source.
- `A16_Report.docx`: editable Word report.
- `Part1_Baseline/`: original project snapshot plus baseline pattern analysis.
- `Part2_Refactoring/`: separate refactoring overlay for Service Layer, Strategy, and Observer.
- `Part3_CrossLanguage/`: small Repository, Strategy, and Observer examples in Java, Python, JavaScript, and C++.
- `data/`: pattern inventory, source inventory, cross-language test matrix, and LLM traceability records.
- `evidence/verification_summary.txt`: exact test status and commands.

## Run the Part 3 tests
- Python: `cd Part3_CrossLanguage/Python && python3 -m unittest -v`
- Java: `cd Part3_CrossLanguage/Java && javac Patterns.java && java Patterns`
- JavaScript: `cd Part3_CrossLanguage/JavaScript && node --test patterns.test.js`
- C++: `cd Part3_CrossLanguage/Cpp && g++ -std=c++17 -O2 patterns_test.cpp -o patterns_test && ./patterns_test`

## Verification boundary
The Part 3 examples passed the checks recorded in `evidence/verification_summary.txt`. The full Spring Boot system and Part 2 overlay have not been built or integration-tested in this environment. The refactoring overlay is a proposal and should be integrated and tested before it is described as production-ready.
