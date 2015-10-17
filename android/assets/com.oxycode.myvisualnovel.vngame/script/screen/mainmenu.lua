local mainmenu = {}

function mainmenu:onCreate(screen)
    print("mainmenu::onCreate()")
    self.screen = screen
<<<<<<< HEAD
    --self.table = screen:newTable()
    self.startGameButton = screen:newButtonFacade()
    self.startGameButton:setOnClick(function(button)
        self.screen:setScreen(R.screen.game)
    end)
    self.startGameButton.setIMagewhenNotClicked();
    --self.table:addChild(self.startGameButton)
=======
    screen:playMusic(R.music.bg1)
    screen:newButton("game", function()
        screen:pushScreen(R.screen.game, {
            lolis = "cute"
        })
    end)
    --[[self.table = screen:newTable()
    self.startGameButton = screen:newButton()
    self.startGameButton:setOnClick(function(button)
        self.screen:setScreen(R.screen.game)
    end)
    self.table:addChild(self.startGameButton)--]]
end

function mainmenu:onResume(params)
    print("mainmenu::onResume()")
    if params ~= nil then
        print(params.oxygen)
    end
end

function mainmenu:onPause()
    print("mainmenu::onPause()")
end

function mainmenu:onDestroy()
    print("mainmenu::onDestroy()")
>>>>>>> 7db70b0346705999c72e43e18d5a2451a9fd9e67
end

return mainmenu
