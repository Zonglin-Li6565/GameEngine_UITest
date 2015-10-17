--local screen={}
local gamebutton={}
local checkBox_1 = {}
local checkBox_2 = {}
local checkBox_3 = {}
local checkBox_4 = {}
local buttongroup = {}

function onCreate(screen)
    x = 1
    --io.write("Again stderr\n")
    gamebutton = screen:createButton("button_1", "", function()
        --print("button clicked")
        screen:print("button clicked")
    end,
        "/mnt/76A0FD4FA0FD15F9/newWorkspace/Tsuru2D/Tsuru2D-engine/android/assets/com.oxycode.myvisualnovel.vngame.zip",
        "image/checkboxes_notChecked.jpg", "image/checkboxes_checked.jpg")
    gamebutton:setPosition(100, 100)
    checkBox_1 = screen:createCheckButton("checkbox_1", "checkbox_1", "/mnt/76A0FD4FA0FD15F9/newWorkspace/Tsuru2D/Tsuru2D-engine/android/assets/com.oxycode.myvisualnovel.vngame.zip",
        "image/checkboxes_notChecked.jpg", "image/checkboxes_checked.jpg")
    checkBox_1:setPosition(250, 100)
    checkBox_2 = screen:createCheckButton("checkbox_2", "checkbox_2", "/mnt/76A0FD4FA0FD15F9/newWorkspace/Tsuru2D/Tsuru2D-engine/android/assets/com.oxycode.myvisualnovel.vngame.zip",
        "image/checkboxes_notChecked.jpg", "image/checkboxes_checked.jpg")
    checkBox_2:setPosition(250, 160)
    checkBox_3 = screen:createCheckButton("checkbox_3", "checkbox_3", "/mnt/76A0FD4FA0FD15F9/newWorkspace/Tsuru2D/Tsuru2D-engine/android/assets/com.oxycode.myvisualnovel.vngame.zip",
        "image/checkboxes_notChecked.jpg", "image/checkboxes_checked.jpg")
    checkBox_3:setPosition(250, 220)
    checkBox_4 = screen:createCheckButton("checkbox_4", "checkbox_4", "/mnt/76A0FD4FA0FD15F9/newWorkspace/Tsuru2D/Tsuru2D-engine/android/assets/com.oxycode.myvisualnovel.vngame.zip",
        "image/checkboxes_notChecked.jpg", "image/checkboxes_checked.jpg")
    checkBox_4:setPosition(250, 280)
    buttongroup = screen:createButtonGroup("buttongroup")
    screen:addToButtonGroup("checkbox_1")
    screen:addToButtonGroup("checkbox_2")
    screen:addToButtonGroup("checkbox_3")
    screen:addToButtonGroup("checkbox_4")
    buttongroup:setMinCheckCount(0)
    buttongroup:setMaxCheckCount(2)

end