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
            setCookie(mlrLangInUse);
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

function setCookie(cValue) {
    localStorage.setItem("lang", cValue);
}

BrowserLanguage = navigator.language.split("-")[0];
console.log(BrowserLanguage);
BrowserLanguageLong = countryCodesArray.find(this2Digit => this2Digit.code === BrowserLanguage);

lang = localStorage.getItem("lang") || "";
if (lang != "") {
    mlrLangInUse = lang;
}
console.log(mlrLangInUse);

if (mlrLangInUse === undefined) {
    mlrLangInUse = BrowserLanguageLong.name;
    setCookie(BrowserLanguageLong.name);
}


mlr({
    dropID: "mbPOCControlsLangDrop",
    stringAttribute: "data-mlr-text",
    chosenLang: mlrLangInUse,
    mLstrings: languages,
    countryCodes: true,
    countryCodeData: countryCodesArray,
});

document.getElementById("mbPOCControlsLangDrop").dispatchEvent(new Event("change"));
