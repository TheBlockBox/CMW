HAI 1.2
CAN I HAS com.google.gson.Gson?
CAN I HAS com.google.gson.GsonBuilder?
CAN I HAS java.io.*?
CAN I HAS STDIO

I HAS A instance ITZ Config_new
I HAS A percentChanceOfWaifu ITZ 10
I HAS CONFIG_FILE ITZ I IZ File_new YR "config" AN YR SUM OF CMW_MODID AN ".json"

HOW IZ I init

IF U SAY SO
I HAS A gson ITZ I IZ Gson_new
I HAS A IT ITZ BOTH SAEM I IZ CONFIG_FILE_exists AN CONFIG_FILE_isFile
O RLY?
	YA RLY
		I HAS A reader ITZ I IZ FileReader_new YR CONFIG_FILE
		instance R I IZ gson_fromJson YR reader AN YR Config_class
		I IZ reader_close
		GTFO
OIC
I HAS A writer ITZ I IZ FileWriter_new YR CONFIG_FILE
I IZ gson_toJson YR instance AN YR writer
I IZ writer_close
KTHXBYE