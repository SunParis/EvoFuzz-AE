from openai import OpenAI

from utils.log import *
from utils.fs import *

class LLMClient:
    
    def __init__(self, api_key: str, base_url: str, model: str, timeout: int):
        self.client = OpenAI(
            api_key=api_key,
            base_url=base_url
        )
        self.model = model
        self.timeout = timeout

    def call_llm(self, input_str: str) -> str | None:
        try:            
            ask_string = input_str
            completion = self.client.chat.completions.create(
                messages=[
                    {
                        "role": "user",
                        "content": ask_string,
                    }
                ],
                model=self.model,
                timeout=self.timeout,
            )
            res = completion.choices[0].message.content
            if res == "" or res is None:
                logging("ERROR", "Error: Empty response from LLM")
                return None
            return res
        except Exception as e:
            logging("ERROR", f"Error calling LLM: {e}")
        return None


# Model.json
# {
#     "data": [
#         {
#             "id": "DeepSeek-R1-671B",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "DeepSeek-R1-Distill-Llama-70B",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "ali-stable-diffusion-v1.5",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "ali-stable-diffusion-xl",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "anthropic/claude-3.5-haiku",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "anthropic/claude-3.5-sonnet",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "anthropic/claude-opus-4",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "anthropic/claude-opus-4.1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "anthropic/claude-sonnet-4",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "babbage-002",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "chatgpt-4o-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "dall-e-2",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "image-generation",
#                 "openai"
#             ]
#         },
#         {
#             "id": "dall-e-3",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "image-generation",
#                 "openai"
#             ]
#         },
#         {
#             "id": "davinci-002",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "deepseek",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek-coder",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek-r1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "coze",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek-reasoner",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "deepseek",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek-v3",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "coze",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek-v3-0324",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek/deepseek-r1:free",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "deepseek/deepseek-v3.1-terminus",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-1.5-flash",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "vertex-ai",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.0-flash",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "vertex-ai",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.0-flash-lite",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-flash",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-flash-preview-04-17",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-pro",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-pro-exp-03-25",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "vertex-ai",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-pro-preview-03-25",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "vertex-ai",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-pro-preview-05-06",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-2.5-pro-preview-06-05",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "gemini-embedding-exp-03-07",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "vertex-ai",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "google/gemini-2.0-flash-001",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "google/gemini-2.0-flash-lite-preview-02-05:free",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "google/gemini-2.0-flash-thinking-exp:free",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "google/gemini-2.0-pro-exp-02-05:free",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "google/gemini-2.5-pro",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "google/gemini-flash-1.5-8b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-3.5-turbo",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-3.5-turbo-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4-turbo",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4.1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4.1-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4.1-nano",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4.5-preview",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o-2024-05-13",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o-2024-08-06",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o-2024-11-20",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o-mini-2024-07-18",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-4o-search-preview",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-5",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-5-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-5-nano",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "gpt-image-1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "image-generation",
#                 "openai"
#             ]
#         },
#         {
#             "id": "o1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "o1-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "o1-preview",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "o3",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "o3-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "o4-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "o4-mini-high",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "openai/gpt-5",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "openai/gpt-5-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "openai/gpt-5-mini",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "openai/gpt-oss-20b:free",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "openai/o1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "openai/o3-pro",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-1.8b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-1.8b-longcontext-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-14b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-72b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-7b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-audio-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-audio-turbo",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-coder-plus",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-coder-plus-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-coder-turbo",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-coder-turbo-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-math-plus",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-math-plus-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-math-turbo",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-math-turbo-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-max",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "coze",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-max-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-max-longcontext",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "ali",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-plus",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "ali",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-plus-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-turbo",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "ali",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-turbo-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-chat-v1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-max",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-max-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-ocr",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-ocr-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-plus",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-plus-latest",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen-vl-v1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen/qwen-vl-plus:free",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen/qwen3-coder",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-0.5b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-1.8b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-110b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-14b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-32b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-72b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen1.5-7b-chat",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-0.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-1.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-57b-a14b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-72b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-7b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-audio-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-math-1.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-math-72b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-math-7b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-vl-2b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2-vl-7b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-0.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-1.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-14b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-32b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-3b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-72b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-7b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-coder-0.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-coder-1.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-coder-14b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-coder-32b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-coder-3b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-coder-7b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-math-1.5b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-math-72b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen2.5-math-7b-instruct",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-0.6b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-1.7b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-14b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-235b-a22b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "ali",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-30b-a3b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-32b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-4b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwen3-8b",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "qwq-32b-preview",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-004",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "vertex-ai",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-005",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "gemini",
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-3-large",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-3-small",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "openai",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-async-v1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-async-v2",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-v1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "ali",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-v2",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "text-embedding-v3",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "wanx-v1",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         },
#         {
#             "id": "x-ai/grok-4",
#             "object": "model",
#             "created": 1626777600,
#             "owned_by": "custom",
#             "supported_endpoint_types": [
#                 "openai"
#             ]
#         }
#     ],
#     "success": true
# }

