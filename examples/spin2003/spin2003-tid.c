// From paper "Thread Modular Model Checking" in SPIN 2003

int x=1, m=0;

#define acquire_thread_id(tid, l) \
  { __blockattribute__((atomic)) \
    assume(l==0); \
    l = tid; \
  } \

void thr1() {
q0: acquire_thread_id(1, m); // m=0 /\ m'=1
q1:  x = 0;
q2:  x = 1;
q3:  assert(x>=1);
q4:  release(m);
}

void thr2() {
  acquire_thread_id(2, m); // m=0 /\ m'=2
  x = 0;
  x = 1;
  assert(x>=1);
  release(m);
}

