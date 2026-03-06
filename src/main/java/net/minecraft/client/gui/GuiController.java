package net.minecraft.client.gui;

import net.minecraft.client.settings.GameSettings;

/*
made by toru
not originally in minecraft source code
*/

public class GuiController extends GuiScreen {

    private GuiScreen parentScreen;
    private GameSettings options;

    protected String screenTitle = "Controller Settings";

    private GuiButton enableControllerButton;
    private GuiSlider interfaceSensitivitySlider;
    private GuiSlider controllerSensitivitySlider;

    private int sliderInterfaceSensitivityId = 4003;
    private int sliderControllerSensitivityId = 4002;
    private int enableControllerId = 4001;
    private int doneId = 4004;

    public GuiController(GuiScreen parent, GameSettings settings) {
        this.parentScreen = parent;
        this.options = settings;
    }

    public void initGui() {

        int centerX = this.width / 2;

        enableControllerButton = new GuiButton(
                enableControllerId,
                centerX - 100,
                this.height / 6 + 20,
                "Controller: " + (options.controllerEnabled ? "ON" : "OFF")
        );

        interfaceSensitivitySlider = new GuiSlider(
                sliderInterfaceSensitivityId,
                centerX - 100,
                this.height / 6 + 50,
                sliderInterfaceSensitivityId,
                "Interface Sensitivity",
                options.interfaceSensitivity
        );

        controllerSensitivitySlider = new GuiSlider(
                sliderControllerSensitivityId,
                centerX - 100,
                this.height / 6 + 80,
                sliderControllerSensitivityId,
                "Controller Sensitivity",
                options.controllerSensitivity
        );

        this.controlList.add(enableControllerButton);
        this.controlList.add(interfaceSensitivitySlider);
        this.controlList.add(controllerSensitivitySlider);

        this.controlList.add(
                new GuiButton(doneId, centerX - 100, this.height / 6 + 150, "Done")
        );
    }

    protected void actionPerformed(GuiButton button) {

        if (!button.enabled) return;

        if (button.id == enableControllerId) {

            options.controllerEnabled = !options.controllerEnabled;

            enableControllerButton.displayString =
                    "Controller: " + (options.controllerEnabled ? "ON" : "OFF");
        }

        if (button.id == doneId) {

            options.interfaceSensitivity = interfaceSensitivitySlider.sliderValue;
            options.controllerSensitivity = controllerSensitivitySlider.sliderValue;

            options.saveOptions();

            this.mc.displayGuiScreen(parentScreen);
        }
    }

    public void drawScreen(int mx, int my, float ticks) {

        options.interfaceSensitivity = interfaceSensitivitySlider.sliderValue;
        options.controllerSensitivity = controllerSensitivitySlider.sliderValue;

        this.drawDefaultBackground();

        this.drawCenteredString(
                this.fontRenderer,
                this.screenTitle,
                this.width / 2,
                20,
                16777215
        );

        super.drawScreen(mx, my, ticks);
    }
}