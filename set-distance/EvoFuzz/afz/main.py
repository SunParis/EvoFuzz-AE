import traceback

from config.config import *
from utils.log import *
from engine.fuzzing import fuzzing

if __name__ == "__main__":
    log_info(f"Start time: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    try:
        import sys
        if len(sys.argv) != 2:
            print("Usage: python main.py <config.yaml>")
            log_info(f"End time: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
            sys.exit(1)
        config: Optional[Config] = load_yaml_config(sys.argv[1])    
        if not config:
            log_info(f"End time: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
            sys.exit(1)
        else:
            log_info("Configuration loaded successfully.")
        fuzzing(config)
    except Exception as e:
        logging("ERROR", f"An error occurred: {e}\n {traceback.format_exc()}")
    log_info(f"End time: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")

