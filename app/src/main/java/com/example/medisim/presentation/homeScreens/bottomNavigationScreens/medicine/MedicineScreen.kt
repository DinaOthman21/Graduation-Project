package com.example.medisim.presentation.homeScreens.bottomNavigationScreens.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.medisim.R
import com.example.medisim.data.remote.dto.Medicine
import com.example.medisim.presentation.components.EditTextWithIcon
import com.example.medisim.presentation.components.LottieAnimationShow
import com.example.medisim.presentation.components.TextLabel
import com.example.medisim.presentation.components.ViewImage
import com.example.medisim.ui.theme.CommonComponent2

@Composable
fun MedicineScreen(medicineViewModel: MedicineScreenViewModel) {
    val searchQuery = medicineViewModel.searchQuery.collectAsState().value


    Column (modifier = Modifier.padding(12.dp)){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            EditTextWithIcon(
                text = searchQuery,
                placeholderID = R.string.enter_name_of_drug,
                iconID = R.drawable.baseline_search_24,
                editTextWidth = 370,
                roundedCornerShapeValue = 28,
                onIconButtonClick = {medicineViewModel.onIconSearchClick() },
                isIconEnabled = true,
                onValueChange = {newValue-> medicineViewModel.onSearchEditTextChange(newValue)}
            )
        }
        if (medicineViewModel.medicine.value == null){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                // show animation
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    LottieAnimationShow(
                        animationResId = R.raw.search_animation,
                        size = 250,
                        padding = 12,
                        paddingBottom = 0
                    )
                }
                TextLabel(
                    text = stringResource(R.string.enter_the_medicine_name_you_search_for),
                    textFontWight = FontWeight.Bold,
                    textFont = 16
                )
            }
        }else{
            // show medicine
            MedicineDetails(medicineViewModel.medicine.value!!)

        }
    }


}



@Composable
fun MedicineDetails(medicine: Medicine) {


    LazyColumn(modifier = Modifier.padding(top = 10.dp)){
        item {
            Column {
                ViewImage(
                    image = medicine.imageLink,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                TextPartOfMedicine(
                    partNameId = R.string.medicine_name,
                    enPart = medicine.medicineEnName,
                    arPart = medicine.medicineArName
                )

                TextPartOfMedicine(
                    partNameId = R.string.scientific_name,
                    enPart = medicine.enScientificName,
                    arPart = medicine.arScientificName
                )
                TextPartOfMedicine(
                    partNameId = R.string.medicine_classification,
                    enPart =  medicine.enMedicineClassification,
                    arPart = medicine.arMedicineClassification
                )

                TextPartOfMedicine(
                    partNameId = R.string.medicine_category,
                    enPart = medicine.enCategory,
                    arPart =medicine.arCategory
                )
                TextPartOfMedicine(
                    partNameId = R.string.medicine_description,
                    enPart = medicine.enDescription,
                    arPart = medicine.arDescription
                )

            }

        }
    }

}


@Composable
fun TextPartOfMedicine(
    partNameId:Int,
    enPart:String,
    arPart:String
) {
    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"


    Column ( modifier = Modifier.padding(top = 10.dp) ){
        TextLabel(
            text = stringResource(partNameId),
            textFont = 22,
            textFontWight = FontWeight.Bold,
            textColor = CommonComponent2
        )
        TextLabel(
            text = if (isArabicLang) arPart else enPart,
            textFont = 18
        )
    }
    
}









val medicineDrugFound = Medicine(
    imageLink = "https://cdn.altibbi.com/cdn/cache/large/image/2023/10/26/1b4bed6391fdb5af607e9b3e4934ad94.webp",
    medicineEnName = "Panadol",
    medicineArName = "بنادول" ,
    enScientificName = "Paracetamol",
    arScientificName = "باراسيتامول",
    enMedicineClassification = "Non-opioid pain reliever",
    arMedicineClassification = "مسكن ألم غير أفيوني",
    enCategory = "Neurological diseases",
    arCategory = "الأمراض العصبية",
    enDescription = "What are the uses of Panadol Advance?\n" +
            "" +
            "This medicine is used for the following indications:\n" +
            "\n" +
            "Headache treatment  .\n" +
            "Fever treatment.\n" +
            "Relieve muscle and joint pain .  \n" +
            "Relieve back pain.\n" +
            "Relieve toothache.\n" +
            "Alleviating cold and flu symptoms.\n" +
            "Paracetamol also relieves menstrual pain in women, and relieves spastic muscle cramps.\n" +
            "\n" +
            "\n" +
            "What are the contraindications for using Panadol Advance?\n" +
            "Paracetamol is contraindicated in the presence of any of the following health problems:\n" +
            "\n" +
            "Hypersensitivity to any component of this medicine.\n" +
            "Severe liver disease or liver failure.\n" +
            "Kidney disease.\n" +
            "\n" +
            "\n" +
            "What are the precautions for using Panadol Advance?\n" +
            "Paracetamol is used with caution and under the supervision of a doctor in the following cases:\n" +
            "\n" +
            "Kidney disease.\n" +
            "Liver diseases.\n" +
            "Alcoholism, that is, if the patient drinks more than 3 drinks a day.\n" +
            "Suffering from low weight or malnutrition.\n" +
            "Suffering from diarrhea or G6PD deficiency.\n" +
            "Suffering from asthma and aspirin allergy.\n" +
            "Suffering from hemolytic anemia.\n" +
            "Suffering from phenylketonuria.\n" +
            "It is also recommended to avoid using other medications that contain paracetamol, such as cold and flu medications, in conjunction with paracetamol, to avoid taking an overdose.\n" +
            "\n" +
            "Paracetamol for pregnant women and children\n" +
            "Paracetamol is one of the medications permitted during pregnancy, especially in the first months. Paracetamol can be used by pregnant women  under medical supervision, and it is also safe to use during breastfeeding.\n" +
            "\n" +
            "It is permissible to use paracetamol for children, as it is available in several pharmaceutical forms suitable for them, such as suppositories, oral drops, and oral syrup, but it is prohibited to exceed the doses prescribed by the doctor to avoid side effects.\n" +
            "\n" +
            "\n" +
            "What are Panadol Advance doses and methods of use?\n" +
            "Paracetamol is taken only when necessary, and the patient should not take all doses if there are no symptoms. \n" +
            "\n" +
            "The doctor determines the appropriate dose for each person according to his health condition, but in general the doses are as follows:\n" +
            "\n" +
            "Paracetamol dosage for adults\n" +
            "For adults and children older than 16 years: 4 g of paracetamol per day, equivalent to 4000 mg, or 8 500 mg tablets, at a rate of one or two tablets every 4 hours, 4 times daily when necessary. The patient may be exposed to serious liver poisoning if he takes an overdose. that.\n" +
            "\n" +
            "Paracetamol dosage for children\n" +
            "Young children under 6 years of age are given paracetamol syrup at a concentration of 120 mg/5 ml in the following doses:\n" +
            "\n" +
            "From 2-3 months: 2.5 ml twice daily.\n" +
            "From 3-6 months: 2.5 ml maximum 4 times daily.\n" +
            "From 6 months to 2 years: 5 ml maximum 4 times daily.\n" +
            "From 2-4 years: 7.5 ml maximum 4 times daily.\n" +
            "From 4-6 years: 10 ml maximum 4 times daily.\n" +
            "Children over the age of 6 years are given paracetamol syrup at a concentration of 250 mg/5 ml in the following doses:\n" +
            "\n" +
            "From 6-8 years: 5 ml maximum 4 times daily.\n" +
            "8-10 years: 7.5 ml maximum 4 times daily.\n" +
            "10-12 years: 10 ml maximum 4 times daily.\n" +
            "From 12-16 years: 10-15 ml maximum 4 times daily.\n",
    arDescription = "ما هي استخدامات بنادول أدفانس؟\n" +
            "يستخدم هذا الدواء للاستطبابات التالية:\n" +
            "\n" +
            "علاج الصداع.\n" +
            "علاج الحمى.\n" +
            "تخفيف آلام العضلات والمفاصل.\n" +
            "تخفيف آلام الظهر.\n" +
            "تخفيف آلام الأسنان.\n" +
            "تخفيف أعراض البرد والزكام.\n" +
            "يعمل الباراسيتامول أيضًا على تخفيف آلام الدورة الشهرية للنساء، كما أنه يخفف من تقلصات العضلات المتشنجة." +
            "ما هي موانع استخدام بنادول أدفانس؟\n" +
            "يمنع استخدام الباراسيتامول في حال وجود أي من المشاكل الصحية التالية:\n" +
            "\n" +
            "فرط الحساسية تجاه أي مكون من مكونات هذا الدواء.\n" +
            "مرض شديد في الكبد أو فشل الكبد.\n" +
            "أمراض الكلى." +
            "ما هي احتياطات استخدام بنادول أدفانس؟\n" +
            "يستخدم دواء باراسيتامول بحذر وتحت إشراف الطبيب في الحالات الآتية:\n" +
            "\n" +
            "أمراض الكلى.\n" +
            "أمراض الكبد.\n" +
            "إدمان الكحول، أي إذا كان المريض يشرب أكثر من 3 كؤوس يوميًا.\n" +
            "المعاناة من انخفاض الوزن أو سوء التغذية.\n" +
            "المعاناة من مرض التفول، أو نقص إنزيم (G6PD).\n" +
            "المعاناة من الربو وحساسية الأسبرين.\n" +
            "المعاناة من فقر الدم الانحلالي.\n" +
            "المعاناة من مرض فينيل كيتون يوريا.\n" +
            "ينصح أيضًا بتجنب استخدام أدوية أخرى تحتوي على الباراسيتامول مثل أدوية الرشح والزكام بالتزامن مع باراسيتامول، تجنبًا لتناول جرعة زائدة.\n" +
            "\n" +
            "باراسيتامول للحامل والأطفال\n" +
            "يعد دواء باراسيتامول من الأدوية المسموح بها خلال فترة الحمل، خاصة في الشهور الأولى، ويمكن استخدام باراسيتامول للحامل تحت الإشراف الطبي، كما أنه آمن الاستخدام خلال فترة الرضاعة الطبيعية.\n" +
            "\n" +
            "يسمح باستخدام الباراسيتامول للأطفال، فهو يتوافر بأشكال صيدلانية متعددة مناسبة لهم، مثل التحاميل، ونقط الفم، والشراب الفموي، لكن يمنع تجاوز الجرعات الموصوفة من قبل الطبيب لتفادي الآثار الجانبية.\n" +
            "ما هي جرعات بنادول أدفانس وطرق الاستعمال؟\n" +
            "يؤخذ باراسيتامول فقط عند اللزوم ولا يجب على المريض تناول جميع الجرعات في حال عدم وجود أعراض. \n" +
            "\n" +
            "يحدد الطبيب الجرعة المناسبة لكل شخص وفقًا لحالته الصحية، لكن بشكل عام الجرعات كالتالي:\n" +
            "\n" +
            "جرعة الباراسيتامول للكبار\n" +
            "للبالغين والأطفال الأكبر من 16 سنة: 4 غم من الباراسيتامول في اليوم، بما يعادل 4000 ملغ أو 8 أقراص بتركيز 500 ملغ، بمعدل قرص واحد أو قرصين كل 4 ساعات 4 مرات يوميًا عند اللزوم، وقد يتعرض المريض لتسمم كبدي خطير إذا تناول جرعة زائدة عن ذلك.\n" +
            "\n" +
            "جرعة الباراسيتامول للأطفال\n" +
            "يعطى الأطفال الصغار الذين تقل أعمارهم عن 6 سنوات شراب باراسيتامول بتركيز 120ملغ/ 5مل بالجرعات التالية:\n" +
            "\n" +
            "من 2-3 شهور: 2.5 مل مرتين يوميًا.\n" +
            "من 3-6 شهور: 2.5 مل 4 مرات يوميًا كحد أقصى.\n" +
            "من 6 شهور إلى سنتين: 5 مل 4 مرات يوميًا كحد أقصى.\n" +
            "من 2-4 سنوات: 7.5 مل 4 مرات يوميًا كحد أقصى.\n" +
            "من 4-6 سنوات: 10 مل 4 مرات يوميًا كحد أقصى.\n" +
            "يعطى الأطفال الذين تزيد أعمارهم عن 6 سنوات شراب باراسيتامول بتركيز 250ملغ/ 5مل بالجرعات التالية:\n" +
            "\n" +
            "من 6-8 سنوات: 5 مل 4 مرات يوميًا كحد أقصى.\n" +
            "من 8-10 سنوات: 7.5 مل 4 مرات يوميًا كحد أقصى.\n" +
            "من 10-12 سنة: 10 مل 4 مرات يوميًا كحد أقصى.\n" +
            "من 12-16 سنة: 10-15 مل 4 مرات يوميًا كحد أقصى."

)
