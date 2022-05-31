var mlrLangInUse;

var mlr = function({
    dropID = "mbPOCControlsLangDrop",
    stringAttribute = "data-mlr-text",
    chosenLang = "German",
    mLstrings = languages,
    countryCodes = true,
    countryCodeData = countryCodesArray,
} = {}) {
    const root = document.documentElement;

    var listOfLanguages = Object.keys(mLstrings[0]);
    mlrLangInUse = chosenLang;

    (function createMLDrop() {
        var mbPOCControlsLangDrop = document.getElementById(dropID);
        // Reset the menu
        mbPOCControlsLangDrop.innerHTML = "";
        // Now build the options
        listOfLanguages.forEach((lang, langidx) => {
            let HTMLoption = document.createElement("option");
            HTMLoption.value = lang;
            HTMLoption.textContent = lang;
            mbPOCControlsLangDrop.appendChild(HTMLoption);
            if (lang === chosenLang) {
                mbPOCControlsLangDrop.value = lang;
            }
        });
        mbPOCControlsLangDrop.addEventListener("change", function(e) {
            mlrLangInUse = mbPOCControlsLangDrop[mbPOCControlsLangDrop.selectedIndex].value;
            resolveAllMLStrings();
            setCookie("mlrLangInUse=", mlrLangInUse, 365);
            // Here we update the 2-digit lang attribute if required
            if (countryCodes === true) {
                if (!Array.isArray(countryCodeData) || !countryCodeData.length) {
                    console.warn("Cannot access strings for language codes");
                    return;
                }
                root.setAttribute("lang", updateCountryCodeOnHTML().code);
            }
        });
    })();

    function updateCountryCodeOnHTML() {
        return countryCodeData.find(this2Digit => this2Digit.name === mlrLangInUse);
    }

    function resolveAllMLStrings() {
        let stringsToBeResolved = document.querySelectorAll(`[${stringAttribute}]`);
        stringsToBeResolved.forEach(stringToBeResolved => {
            let originaltextContent = stringToBeResolved.textContent;
            let resolvedText = resolveMLString(originaltextContent, mLstrings);
            stringToBeResolved.textContent = resolvedText;
        });
    }
};

function resolveMLString(stringToBeResolved, mLstrings) {
    var matchingStringIndex = mLstrings.find(function(stringObj) {
        // Create an array of the objects values:
        let stringValues = Object.values(stringObj);
        // Now return if we can find that string anywhere in there
        return stringValues.includes(stringToBeResolved);
    });
    if (matchingStringIndex) {
        return matchingStringIndex[mlrLangInUse];
    } else {
        // If we don't have a match in our language strings, return the original
        return stringToBeResolved;
    }
}

function setCookie(cName, cValue, expDays) {
    console.log("Setting cookie: " + cName + " to " + cValue);
    let date = new Date();
    date.setTime(date.getTime() + (expDays * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = cName + "=" + cValue + "; " + expires + "; path=/";
}

BrowserLanguage = navigator.language.split("-")[0];
console.log(BrowserLanguage);
BrowserLanguageLong = countryCodesArray.find(this2Digit => this2Digit.code === BrowserLanguage);
console.log(BrowserLanguageLong);

cookies = document.cookie;
console.log(cookies);
if (cookies != "") {
    forEach(cookies, function(cookie) {
        var parts = cookie.split("=");
        var name = parts[0].trim();
        var value = parts[1].trim();
        if (name === "mlrLangInUse") {
            mlrLangInUse = value;
        }
    });
}
console.log(mlrLangInUse);

if (mlrLangInUse === undefined) {
    mlrLangInUse = BrowserLanguageLong.name;
    setCookie("mlrLangInUse=", BrowserLanguageLong.name, 365);
}
console.log(mlrLangInUse);


mlr({
    dropID: "mbPOCControlsLangDrop",
    stringAttribute: "data-mlr-text",
    chosenLang: mlrLangInUse,
    mLstrings: languages,
    countryCodes: true,
    countryCodeData: countryCodesArray,
});

document.getElementById("mbPOCControlsLangDrop").dispatchEvent(new Event("change"));
