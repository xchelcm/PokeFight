import SwiftUI
import XCPokeFight
import Foundation

struct ContentView: View {
    let greet = Greeting().greet()
    let value1 = SharedCommonTesting.companion.testing
    let value2 = "SharedAndroidTesting is not available"//SharedAndroidTesting.testing
    let value3 = "AndroidTesting is not available"// AndroidTesting.testing
    let value4 = SharedIOSTesting.companion.testing
    
    var viewModel = HomeViewModel()
    
    
    var body: some View {
        
        //         Observing(viewModel.state) state in { Text("state.pokemons.count") }
//        Observing(viewModel.state) { state in
//            VStack {
//                Text("State:\(state.isLoading)")
//                Text("iOS App")
//                Text(value1)
//                Text(value2)
//                Text(value3)
//                Text(value4)
//            }
//        }
        VStack {
            Observing(viewModel.state) { state in
                VStack {
                    Text("Pokemon")
                    if state.isLoading {
                        ProgressView()
                    } else {
                        List(state.pokemonList, id: \.name) { pokemon in
                            Text(pokemon.name)
                        }
                    }
                }
            }
        }
        .onAppear {
            viewModel.getPokemon()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
