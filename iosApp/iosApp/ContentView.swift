import SwiftUI
import PokeFight

struct ContentView: View {
	let greet = Greeting().greet()
    let value1 = SharedCommonTesting.companion.testing
    let value2 = "SharedAndroidTesting is not available"//SharedAndroidTesting.testing
    let value3 = "AndroidTesting is not available"// AndroidTesting.testing
    let value4 = SharedIOSTesting.companion.testing

	var body: some View {
        VStack {
            Text("iOS App")
            Text(value1)
            Text(value2)
            Text(value3)
            Text(value4)
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
