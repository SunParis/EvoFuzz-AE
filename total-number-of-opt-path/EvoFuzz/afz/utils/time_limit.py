from contextlib import contextmanager
import signal

class TimeoutErrorReached(Exception):
    """Custom exception to indicate a timeout has occurred."""
    pass

@contextmanager
def time_limit(seconds: int):
    def _handler(signum, frame):
        raise TimeoutErrorReached(f"Timed out after {seconds}s")
    old = signal.signal(signal.SIGALRM, _handler)
    signal.alarm(seconds)
    try:
        yield
    finally:
        signal.alarm(0)
        signal.signal(signal.SIGALRM, old)